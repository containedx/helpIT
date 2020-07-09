package com.helpit.services;

import com.helpit.repositories.RoleRepository;
import com.helpit.repositories.TypesRepository;
import com.helpit.repositories.UserRepository;
import com.helpit.model.Role;
import com.helpit.model.Type;
import com.helpit.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;

@Service("userService")
@Transactional
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TypesRepository typesRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail (String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserByUsername (String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findUser(String name){
       User user = userRepository.findByEmail(name);
        if(user==null){
            user=userRepository.findByUsername(name);
        }
        return user;
    }
    @Override
    public void saveVolunteer (User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        user.getVolunteer().setPosts(new HashSet<>());
        user.getVolunteer().setComments(new HashSet<>());
        user.getVolunteer().setCommentsUnderPost(new HashSet<>());

        Role role = roleRepository.findByRole("ROLE_VOLUNTEER");
        user.setRole(role);
        userRepository.save(user);
    }


    @Override
    public void saveFoundation (User user) {
        if(!user.getPassword().equals(user.getConfirmPassword()))
            return;
        user.getFoundation().setPost(new HashSet<>());
        user.getFoundation().setComment(new HashSet<>());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        String type_name = user.getFoundation().getType().getType();
        Type type = typesRepository.findByType(type_name);
        user.getFoundation().setType(type);
        Role role = roleRepository.findByRole("ROLE_FOUNDATION");
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public void updateUser (String id, User user) {
        User u = userRepository.findById(Integer.parseInt(id)).get();
        if(user.getPassword()!=null)
            u.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if(user.getEmail()!=null)
            u.setEmail(user.getEmail());
        if(user.getAddress().getCity()!=null)
            u.getAddress().setCity(user.getAddress().getCity());
        if(user.getAddress().getPostcode()!=null)
            u.getAddress().setPostcode(user.getAddress().getPostcode());
        if(user.getAddress().getStreet()!=null)
            u.getAddress().setStreet(user.getAddress().getStreet());
        if(user.getAddress().getNumberOfHome()!=null)
            u.getAddress().setNumberOfHome(user.getAddress().getNumberOfHome());
        if(user.getAddress().getNumberOfFlat()!=0)
            u.getAddress().setNumberOfFlat(user.getAddress().getNumberOfFlat());

        userRepository.save(u);
    }

    @Override
    public void banUser (String id) {
        User u = userRepository.findById(Integer.parseInt(id)).get();
        u.setActive(0);
        userRepository.save(u);
    }

    @Override
    public void unbanUser(String id){
        User u = userRepository.findById(Integer.parseInt(id)).get();
        u.setActive(1);
        userRepository.save(u);

    }

    @Override

    public void deleteUser (String id) {
        userRepository.delete(userRepository.findById(Integer.parseInt(id)).get());
    }




}

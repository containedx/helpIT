package com.helpit.services;

<<<<<<< HEAD
import com.helpit.model.User;
import org.springframework.stereotype.Service;
=======
import com.helpit.user.FoundationVol;
import com.helpit.user.User;
>>>>>>> kamil-skorupa

@Service
public interface UserService {
    public User findUserByEmail(String email);
    public User findUserByUsername(String username);
    public User findUser(String name);
    public void saveVolunteer(User user);
    public void saveFoundation(User user);


}

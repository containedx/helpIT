package com.helpit.services;


import com.helpit.model.User;
import org.springframework.stereotype.Service;

import com.helpit.model.FoundationVol;
import com.helpit.model.User;


@Service
public interface UserService {
    public User findUserByEmail(String email);
    public User findUserByUsername(String username);
    public User findUser(String name);
    public void saveVolunteer(User user);
    public void saveFoundation(User user);


}

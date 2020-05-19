package com.helpit.services;

import com.helpit.user.User;

public interface UserService {
    public User findUserByEmail(String email);
    public void saveVolunteer(User user);
    public void saveFoundation(User user);
}

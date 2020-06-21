package com.helpit.services;

import com.helpit.user.FoundationVol;
import com.helpit.user.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface FoundationVolService {

    public void saveVolunteer(User user);
    public void saveFoundation(User user);


}

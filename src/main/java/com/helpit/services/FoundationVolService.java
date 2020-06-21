package com.helpit.services;

import com.helpit.model.FoundationVol;
import com.helpit.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface FoundationVolService {

    public void saveVolunteer(User user);
    public void saveFoundation(User user);


}

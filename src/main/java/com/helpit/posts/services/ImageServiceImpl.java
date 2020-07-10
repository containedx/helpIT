package com.helpit.posts.services;

import com.helpit.model.Foundation;
import com.helpit.model.User;
import com.helpit.model.Volunteer;
import com.helpit.repositories.FoundationRepository;
import com.helpit.repositories.UserRepository;
import com.helpit.repositories.VolunteerRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public class ImageServiceImpl implements ImageService {
    private final FoundationRepository foundationRepository;
    private final VolunteerRepository volunteerRepository;
    private final UserRepository userRepository;

    public ImageServiceImpl(FoundationRepository foundationRepository, VolunteerRepository volunteerRepository, UserRepository userRepository) {
        this.foundationRepository = foundationRepository;
        this.volunteerRepository = volunteerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void saveImageFileToFoundation(MultipartFile file) {
        //System.out.println("Image has been loaded");
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String currentUserName = auth.getName();
            User loggedUser = userRepository.findByEmail(currentUserName);
            Foundation p = loggedUser.getFoundation();

            int i = 0;
            Byte[] byteObject = new Byte[file.getBytes().length];
            for ( byte b : file.getBytes()) {
                byteObject[i++] = b;
            }

            p.setImage(byteObject);
            foundationRepository.save(p);
        }
        catch (Exception e) {
            System.out.println("Error while persisting file");
            e.printStackTrace();
        }
    }

    @Override
    public void saveImageFileToVolunteer(MultipartFile file) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String currentUserName = auth.getName();
            User loggedUser = userRepository.findByEmail(currentUserName);
            Volunteer p = loggedUser.getVolunteer();

            int i = 0;
            Byte[] byteObject = new Byte[file.getBytes().length];
            for ( byte b : file.getBytes()) {
                byteObject[i++] = b;
            }

            p.setImage(byteObject);

            volunteerRepository.save(p);
            userRepository.save(loggedUser);

        }
        catch (Exception e) {
            System.out.println("Error while persisting file");
            e.printStackTrace();
        }
    }


}

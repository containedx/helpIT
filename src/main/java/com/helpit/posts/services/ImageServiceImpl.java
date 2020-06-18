package com.helpit.posts.services;

import com.helpit.model.Foundation;
import com.helpit.model.Volunteer;
import com.helpit.repositories.FoundationRepository;
import com.helpit.repositories.VolunteerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public class ImageServiceImpl implements ImageService {
    private final FoundationRepository foundationRepository;
    private final VolunteerRepository volunteerRepository;

    public ImageServiceImpl(FoundationRepository foundationRepository, VolunteerRepository volunteerRepository) {
        this.foundationRepository = foundationRepository;
        this.volunteerRepository = volunteerRepository;
    }

    @Override
    public void saveImageFileToFoundation(Integer id, MultipartFile file) {
        //System.out.println("Image has been loaded");
        try {
            Foundation p = foundationRepository.findById(id).get();

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
    public void saveImageFileToVolunteer(Integer id, MultipartFile file) {
        try {
            Volunteer p = volunteerRepository.findById(id).get();

            int i = 0;
            Byte[] byteObject = new Byte[file.getBytes().length];
            for ( byte b : file.getBytes()) {
                byteObject[i++] = b;
            }

            p.setImage(byteObject);
            volunteerRepository.save(p);
        }
        catch (Exception e) {
            System.out.println("Error while persisting file");
            e.printStackTrace();
        }
    }


}

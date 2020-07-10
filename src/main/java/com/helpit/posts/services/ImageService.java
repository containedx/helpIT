package com.helpit.posts.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    public void saveImageFileToFoundation(MultipartFile file);
    public void saveImageFileToVolunteer(MultipartFile file);
}

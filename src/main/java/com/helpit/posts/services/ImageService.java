package com.helpit.posts.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    public void saveImageFileToFoundation(Integer id, MultipartFile file);
    public void saveImageFileToVolunteer(Integer id, MultipartFile file);
}

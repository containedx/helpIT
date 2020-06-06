package com.helpit.posts.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    public void saveImageFile(Long postID, MultipartFile file);
}

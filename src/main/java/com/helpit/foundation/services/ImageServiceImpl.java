package com.helpit.foundation.services;

import com.helpit.foundation.model.Post;
import com.helpit.foundation.repositories.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public class ImageServiceImpl implements ImageService {
    private final PostRepository post_repository;

    public ImageServiceImpl(PostRepository repository) {
        this.post_repository = repository;
    }


    @Override
    public void saveImageFile(Long postID, MultipartFile file) {
        //System.out.println("Image has been loaded");
        try {
            Post post = post_repository.findById(postID).get();

            int i = 0;
            Byte[] byteObject = new Byte[file.getBytes().length];
            for ( byte b : file.getBytes()) {
                byteObject[i++] = b;
            }

            post.setImage(byteObject);
            post_repository.save(post);
        }
        catch (Exception e) {
            System.out.println("Error while persisting file");
            e.printStackTrace();
        }
    }


}

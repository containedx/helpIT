package com.helpit.posts.controllers;

import com.helpit.posts.model.Post;
import com.helpit.posts.services.ImageServiceImpl;
import com.helpit.posts.repositories.PostRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Optional;

@Controller
public class ImageController {
    private PostRepository post_repository;
    private final ImageServiceImpl image_service;

    public ImageController(PostRepository post_repository, ImageServiceImpl image_service) {
        this.post_repository = post_repository;
        this.image_service = image_service;
    }

    @RequestMapping("/add_post/{id}/addImage")
    public String getImageForm(@PathVariable String id, Model model)
    {
        Optional<Post> post = post_repository.findById(Integer.valueOf(id));
        if(post.isPresent()){
            model.addAttribute("post", post.get());
        }
        else {
            throw new RuntimeException("Cannot add image to post, because it is not present in the database");
        }

        return "add_post/image_form";
    }

    @RequestMapping("/post/{id}/image")
    public String persistImage(@PathVariable String id, @RequestParam("imagefile")MultipartFile file, Model model) {

        image_service.saveImageFile(Integer.valueOf(id), file);
        Optional<Post> post = post_repository.findById(Integer.valueOf(id));
        if (post.isPresent()) {
            model.addAttribute("post", post.get());
        }
        else {
            throw new RuntimeException("Sth went wrong in imageController");
        }

        return "add_post/display_post";
    }

    @GetMapping("/post/{id}/renderimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) {

        try {
            Post post = post_repository.findById(Integer.valueOf(id)).get();

            if( post.getImage() != null ) {
                byte[] unwrapped_image = new byte[post.getImage().length];

                int i = 0;
                for ( Byte b : post.getImage() ) {
                    unwrapped_image[i++] = b;
                }

                response.setContentType("image/jpeg");
                InputStream is = new ByteArrayInputStream(unwrapped_image);
                IOUtils.copy(is, response.getOutputStream());
            }

        }
        catch (Exception e) {
            System.out.println("Error while rendering image from DB");
            e.printStackTrace();
        }

    }
}

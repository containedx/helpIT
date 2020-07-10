package com.helpit.posts.controllers;

import com.helpit.model.Foundation;
import com.helpit.model.User;
import com.helpit.model.Volunteer;
import com.helpit.posts.model.Post;
import com.helpit.posts.services.ImageServiceImpl;
import com.helpit.posts.repositories.PostRepository;
import com.helpit.repositories.FoundationRepository;
import com.helpit.repositories.UserRepository;
import com.helpit.repositories.VolunteerRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
public class ImageController {
    private final FoundationRepository foundationRepository;
    private final VolunteerRepository volunteerRepository;
    private final ImageServiceImpl imageService;
    private final UserRepository userRepository;

    public ImageController(FoundationRepository foundationRepository, VolunteerRepository volunteerRepository, ImageServiceImpl imageService, UserRepository userRepository) {
        this.foundationRepository = foundationRepository;
        this.volunteerRepository = volunteerRepository;
        this.imageService = imageService;
        this.userRepository = userRepository;
    }

//    @RequestMapping("/foundation/{id}/addImage")
//    public String getImageForm(@PathVariable String id, Model model)
//    {
//        Optional<Foundation> p = foundationRepository.findById(Integer.valueOf(id));
//        if(p.isPresent()){
//            model.addAttribute("foundation", p.get());
//        }
//        else {
//            throw new RuntimeException("Cannot add image to foundation, because it is not present in the database");
//        }
//
//        return "add_post/image_form"; //zmien to !!!
//    }

//    @RequestMapping("/foundation/{id}/image")
//    public String persistImage(@PathVariable String id, @RequestParam("imagefile")MultipartFile file, Model model) {
//
//        imageService.saveImageFileToFoundation(Integer.valueOf(id), file);
//        Optional<Foundation> p = foundationRepository.findById(Integer.valueOf(id));
//        if(p.isPresent()){
//            model.addAttribute("foundation", p.get());
//        }
//        else {
//            throw new RuntimeException("Sth went wrong in imageController");
//        }
//
//        return "add_post/display_post"; //zmien to !!
//    }

    @GetMapping("/foundation/{id}/renderimage")
    public void renderImageFromDBforFoundation(@PathVariable String id, HttpServletResponse response) {

        try {
            Foundation p = foundationRepository.findById(Integer.valueOf(id)).get();

            if( p.getImage() != null ) {
                byte[] unwrapped_image = new byte[p.getImage().length];

                int i = 0;
                for ( Byte b : p.getImage() ) {
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

    @GetMapping("/volunteer/{id}/renderimage")
    public void renderImageFromDBforVolunteer(@PathVariable String id, HttpServletResponse response) {

        try {
            Volunteer p = volunteerRepository.findById(Integer.valueOf(id)).get();

            if( p.getImage() != null ) {
                byte[] unwrapped_image = new byte[p.getImage().length];

                int i = 0;
                for ( Byte b : p.getImage() ) {
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

    @RequestMapping("/volunteer/image")
    public String submitImageToVolunteer(@RequestParam("avatar") MultipartFile file,
                                         Model model) {
        imageService.saveImageFileToVolunteer(file);
        return "redirect:/volunteer";
    }

    @RequestMapping("/foundation/image")
    public String submitImageToFoundation(@RequestParam("avatar") MultipartFile file,
                                         Model model) {
        imageService.saveImageFileToFoundation(file);
        return "redirect:/foundation";
    }
}

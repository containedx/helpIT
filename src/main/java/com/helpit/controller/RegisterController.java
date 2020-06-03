package com.helpit.controller;

import com.helpit.services.UserService;
import com.helpit.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.POST;
import java.util.Locale;
import java.util.Optional;

@RestController
public class RegisterController {
    @Autowired
    private UserService userService;

    @Autowired
    MessageSource messageSource;

    @PostMapping(value = "/signup/add_foundation")
    public String registerFormFoundation(@ModelAttribute("user") @Valid User user, BindingResult result, Model model, Locale locale){
        String returnPage="";
        User userExist = userService.findUserByEmail(user.getEmail());

        if(userExist!=null){
            result.rejectValue("email",messageSource.getMessage("error.user.email.exists",null,locale));
        }
        else {
            userExist = userService.findUserByUsername(user.getUsername());
            if(userExist!=null){
                result.rejectValue("email",messageSource.getMessage("error.user.username.exists",null,locale));
            }
        }
        if(result.hasErrors()) {
            returnPage="signup/foundation";
        }else{
            model.getAttribute("");
            userService.saveFoundation(user);
            model.addAttribute("message",messageSource.getMessage("user.register.success",null,locale));
           // model.addAttribute("user",new User());
            returnPage="redirect:/login";
        }
        return returnPage;
    }


    @PostMapping(value = "/signup/add_volunteer")
    public String registerFormVolunteer(@ModelAttribute("user") @Valid User user, BindingResult result, Model model, Locale locale){
        String returnPage=null;
        User userExist = userService.findUserByEmail(user.getEmail());

        if(userExist!=null){
            result.rejectValue("email",messageSource.getMessage("error.user.email.exists",null,locale));
        }
        else {
            userExist = userService.findUserByUsername(user.getUsername());
            if(userExist!=null){
                result.rejectValue("email",messageSource.getMessage("error.user.username.exists",null,locale));
            }
        }

        if(result.hasErrors()) {
            returnPage = "redirect:/registration/volunteer";
        }else{
            userService.saveVolunteer(user);
            model.addAttribute("message",messageSource.getMessage("user.register.success",null,locale));
            returnPage="login";
        }
        return returnPage;
    }
    /*
    @GetMapping("signup/add_volunteer")
    public String returnRegisterFormVolunteer(Model model){
        User u = new User();
        model.addAttribute("user",u);
        return "registration/volunteer";
    }

    @GetMapping("signup/add_foundation")
    public String returnRegisterFormFoundation(Model model){
        User u = new User();
        model.addAttribute("user",u);
        return "registration/foundation";
    }*/

}

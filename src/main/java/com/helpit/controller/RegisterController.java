package com.helpit.controller;

import com.helpit.services.UserService;
import com.helpit.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.POST;
import java.util.Locale;

@RestController
public class RegisterController {
    @Autowired
    private UserService userService;

    @Autowired
    MessageSource messageSource;

    @PostMapping(value = "/signup/add_foundation")
    public String registerForm(User user, BindingResult result, Model model, Locale locale){
        String returnPage=null;
        User userExist = userService.findUserByEmail(user.getEmail());
        if(userExist!=null){
            result.rejectValue("email",messageSource.getMessage("error.user.email.exists",null,locale));
        }
        if(result.hasErrors()) {
            returnPage = "registration/foundation";
        }else{
            userService.saveFoundation(user);
            model.addAttribute("message",messageSource.getMessage("user.register.success",null,locale));
           // model.addAttribute("user",new User());
            returnPage="login";
        }
        return returnPage;
    }

}

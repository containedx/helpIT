package com.helpit.controllers;

import com.helpit.controllers.validators.FoundationRegisterValidator;
import com.helpit.controllers.validators.VolunteerRegisterValidator;
import com.helpit.services.UserService;
import com.helpit.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.Locale;

@Controller
public class RegisterController {

    private final UserService userService;

    final MessageSource messageSource;

    @Autowired
    public RegisterController (UserService userService, MessageSource messageSource) {
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @PostMapping(value = "/signup/add_foundation")
    public String registerFormFoundation (@Valid User user, BindingResult result, Model model, Locale locale) {

        String returnPage = "redirect:/signup/foundation";

        User userExist = userService.findUserByEmail(user.getEmail());

        FoundationRegisterValidator foundationRegisterValidator = new FoundationRegisterValidator();
        foundationRegisterValidator.validate(user, result);

        foundationRegisterValidator.validateEmailExist(userExist, result);
        userExist = userService.findUserByUsername(user.getUsername());
        foundationRegisterValidator.validateUsernameExist(userExist, result);

        if (!result.hasErrors()) {
            userService.saveFoundation(user);
            returnPage = "redirect:/login";
        }
        return returnPage;
    }


    @PostMapping(value = "/signup/add_volunteer")
    public String registerFormVolunteer (@Valid User user, BindingResult result, Model model, Locale locale) {
        String returnPage = "redirect:/signup/volunteer";

        User userExist = userService.findUserByEmail(user.getEmail());

        VolunteerRegisterValidator volunteerRegisterValidator = new VolunteerRegisterValidator();
        volunteerRegisterValidator.validate(user, result);

        volunteerRegisterValidator.validateEmailExist(userExist, result);
        userExist = userService.findUserByUsername(user.getUsername());
        volunteerRegisterValidator.validateUsernameExist(userExist, result);

        if (!result.hasErrors()) {
            userService.saveVolunteer(user);
            returnPage = "redirect:/login";
        }

        return returnPage;
    }

    @GetMapping("signup/add_foundation")
    public String redirectAddFoundationPage (WebRequest request) {
        return "redirect:/signup/foundation";
    }

    @GetMapping("signup/add_volunteer")
    public String redirectAddVolunteerPage (WebRequest request) {
        return "redirect:/signup/volunteer";
    }

}

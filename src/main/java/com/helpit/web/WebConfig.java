package com.helpit.web;

import com.helpit.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.HashSet;
import java.util.Set;

@Configuration
@Controller
public class WebConfig {

    @GetMapping("/signup")
    public String showRegistrationPage(){
        return "registration/signup";
    }

    @GetMapping("/login")
    public String showLoginPage(){
        return "login";
    }

    @GetMapping("/logout")
    public String showLogOutPage(){
        return "index";
    }

    @GetMapping("signup/foundation")
    public String showRegistrationFormForFoundation(WebRequest request, Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "registration/foundation";
    }

    @GetMapping("signup/volunteer")
    public String showRegistrationFormForVolunteer(WebRequest request, Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "registration/volunteer";
    }

    @RequestMapping({"/volunteer"})
    public String getVolunteer()
    {
        return "/volunteer/show";
    }

    @RequestMapping({"/user/manager"})
    public String getUserManager()
    {
        return "manager";
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

}
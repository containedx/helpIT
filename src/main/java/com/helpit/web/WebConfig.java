package com.helpit.web;

import com.helpit.user.Address;
import com.helpit.user.Foundation;
import com.helpit.user.User;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Controller
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/signup").setViewName("registration/signup");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/logout").setViewName("index");
        registry.addViewController("/signup/add_foundation").setViewName("index");
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


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }


}

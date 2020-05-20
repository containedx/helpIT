package com.helpit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/error").setViewName("error");
        registry.addViewController("/").setViewName("index");

        registry.addViewController("/login/charity").setViewName("sessions/new_charity");
        registry.addViewController("/login/volunteer").setViewName("sessions/new_volunteer");

        registry.addViewController("/signup/charity").setViewName("registrations/new_charity");
        registry.addViewController("/signup/volunteer").setViewName("registrations/new_volunteer");

        registry.addViewController("/charity/show").setViewName("charity/show");
        registry.addViewController("/charity/edit").setViewName("registrations/edit_charity");

        registry.addViewController("/article/show").setViewName("article/show");

    }
}


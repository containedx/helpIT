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
        registry.addViewController("/login/charity").setViewName("session/new_charity");
        registry.addViewController("/login/volunteer").setViewName("session/new_volunteer");
        registry.addViewController("/signup/charity").setViewName("registration/charity");
        registry.addViewController("/signup/volunteer").setViewName("registration/volunteer");

    }
}


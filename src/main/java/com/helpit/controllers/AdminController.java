package com.helpit.controllers;

import com.helpit.model.SearchedFoundation;
import com.helpit.model.User;
import com.helpit.repositories.UserRepository;
import com.helpit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {
    UserRepository userRepository;
    UserService userService;

    @Autowired
    AdminController(UserRepository userRepository,UserService userService){
        this.userRepository=userRepository;
        this.userService=userService;
    }

    @RequestMapping(value={"/admin"})
    public String showAdminPage(Model model){
        List<User> volunteerList = new ArrayList<>();
        List<User> foundationList = new ArrayList<>();
        List<User> userList = new ArrayList<>();

        foundationList=userRepository.findAllByRole_Role("ROLE_FOUNDATION");
        volunteerList=userRepository.findAllByRole_Role("ROLE_VOLUNTEER");
        userList.addAll(volunteerList);
        userList.addAll(foundationList);
        model.addAttribute("userList",userList);
        return "admin/index";
    }

    @RequestMapping( value="/admin/{id}/update")
    public String showUpdateUser( @PathVariable String id,Model model){
        User u =userRepository.findById(Integer.parseInt(id)).get();
        model.addAttribute("user",u);
        return "admin/update";
    }

    @RequestMapping(method=RequestMethod.POST,value="/admin/{id}/update")
    public String updateUser(User user, @PathVariable String id){
        userService.updateUser(id,user);
        return "redirect:/admin";
    }


    @RequestMapping( value="/admin/{id}/ban")
    public String banUser(@PathVariable String id){
        userService.banUser(id);
        return "redirect:/admin";

    }

    @RequestMapping( value="/admin/{id}/unban")
    public String unbanUser( @PathVariable String id){
        userService.unbanUser(id);
        return "redirect:/admin";
    }



}

package com.helpit.search;

import com.helpit.model.SearchedFoundation;
import com.helpit.model.Types;
import com.helpit.model.User;
import com.helpit.repositories.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class SearchController {

    UserRepository userRepository;

    @Autowired
    SearchController(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @PostMapping(value = "/search")
    public String returnListOfFoundation(@ModelAttribute("userFoundation")SearchedFoundation user, WebRequest request, Model model){
        List<User> userList = new ArrayList<>();
        if(!user.getCity().isEmpty()) {
            userList = userRepository.findByAddress_City(user.getCity());
            if(!user.getName().isEmpty()){
                userList=userList.stream().filter(e->e.getFoundation().getName().contains(user.getName())).collect(Collectors.toList());
            }
        }
        else if(!user.getName().isEmpty())
            userList=userRepository.findAllByFoundation_NameContaining(user.getName());
        else
            userList=userRepository.findAll();
        Set<User> temp = new LinkedHashSet<>();
        boolean checked=false;
        if(user.getFamily()){
            temp.addAll(userList.stream().filter(e->e.getFoundation().getType().getType().equals(Types.family.getTypeName())).collect(Collectors.toSet()));
            checked=true;
        }
        if(user.getPeace()){
            temp.addAll(userList.stream().filter(e->e.getFoundation().getType().getType().equals(Types.peace.getTypeName())).collect(Collectors.toList()));
            checked=true;

        }

        if(user.getEnvironment()){
            temp.addAll(userList.stream().filter(e->e.getFoundation().getType().getType().equals(Types.environment.getTypeName())).collect(Collectors.toList()));
            checked=true;

        }

        if(user.getDisease()){
            temp.addAll(userList.stream().filter(e->e.getFoundation().getType().getType().equals(Types.disease.getTypeName())).collect(Collectors.toList()));
            checked=true;

        }

        if(user.getEducation()){
            temp.addAll(userList.stream().filter(e->e.getFoundation().getType().getType().equals(Types.education.getTypeName())).collect(Collectors.toList()));
            checked=true;

        }

        if(user.getCulture()){
            temp.addAll(userList.stream().filter(e->e.getFoundation().getType().getType().equals(Types.culture.getTypeName())).collect(Collectors.toList()));
            checked=true;

        }

        if(user.getAnimals()){
            temp.addAll(userList.stream().filter(e->e.getFoundation().getType().getType().equals(Types.animals.getTypeName())).collect(Collectors.toList()));
            checked=true;

        }

        if(user.getAddiction()){
            temp.addAll(userList.stream().filter(e->e.getFoundation().getType().getType().equals(Types.addiction.getTypeName())).collect(Collectors.toList()));
            checked=true;

        }
        if(checked)
            userList=temp.stream().collect(Collectors.toList());

        model.addAttribute("listFoundations",userList);
        return "index";
    }
    @RequestMapping(value={"/","/search"})
    public String showHomePage(Model model){
        List<User> foundations = new ArrayList<>();
        SearchedFoundation user = new SearchedFoundation();

        foundations=userRepository.findAll();
        model.addAttribute("listFoundations",foundations);
        model.addAttribute("userFoundation",user);
        return "index";
    }



}

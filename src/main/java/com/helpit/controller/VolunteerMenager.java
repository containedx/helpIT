package com.helpit.controller;

import com.helpit.repositories.FoundationVolRepository;
import com.helpit.repositories.UserRepository;
import com.helpit.services.UserService;
import com.helpit.user.FoundationVol;
import com.helpit.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class VolunteerMenager {
    @Autowired
    private FoundationVolRepository repo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private UserService userService;

    private int numberValue = 0;

    public String currentUserName(Principal principal) {
        return principal.getName();
    }

/*
    @GetMapping("/index")
    public String view(Model model){
        List <FoundationVol> VolList = listALL();
        model.addAttribute("ListVol",VolList);
        return "check";
    }
*/

    @RequestMapping("/check")
    public List<FoundationVol> check(Model model) {
        List <FoundationVol> VolList = listALL();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = auth.getName();
        User userExist = userService.findUserByEmail(currentUserName);
        int id = userExist.getId();
        find(VolList, id);
        model.addAttribute("ListVol", VolList);

        return VolList;
    }


    private List<FoundationVol> listALL() {
        return repo.findAll();
    }
    private  List<FoundationVol> find (List<FoundationVol> list, int id ){
        list.removeIf(p->p.getFoundation_id() != id);
        return list;
    }
}

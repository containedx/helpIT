package com.helpit.controller;

import com.helpit.repositories.FoundationRepository;
import com.helpit.repositories.FoundationVolRepository;
import com.helpit.repositories.UserRepository;
import com.helpit.repositories.VolunteerRepository;
import com.helpit.services.UserService;
import com.helpit.user.Foundation;
import com.helpit.user.FoundationVol;
import com.helpit.user.User;
import com.helpit.user.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;


import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class VolunteerMenager {
    @Autowired
    private FoundationVolRepository repo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private FoundationRepository funRepo;
    @Autowired
    private VolunteerRepository Volrepo;
    private int numberValue = 0;

    public String currentUserName(Principal principal) {
        return principal.getName();
    }


    @RequestMapping(value="/vol",method=RequestMethod.GET)
    public String Menu(){
        return "VolunteerMenagment/vol";
    }


    @RequestMapping("/checkVolunteer")
    public String checkVolunteer(Model model) {
        List <FoundationVol> VolList = listALL();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = auth.getName();
        User userExist = userService.findUserByEmail(currentUserName);
        int id = userExist.getFoundation().getFoundation_id();
        find(VolList, id);
        model.addAttribute("ListVol", VolList);

        return "VolunteerMenagment/vol";
    }
    @RequestMapping("/checkFoundations")
    public String checkFoundation(Model model) {
        List <FoundationVol> FunList = listALL();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = auth.getName();
        User userExist = userService.findUserByEmail(currentUserName);
        int id = userExist.getVolunteer().getVolunteer_id();
        findFun(FunList, id);
        model.addAttribute("FunList", FunList);

        return "VolunteerMenagment/vol";
    }
    @GetMapping("/add")
    public String addVolunteer(WebRequest request, Model model) {
        FoundationVol vol = new FoundationVol();
        model.addAttribute("vol", vol);
        return "VolunteerMenagment/add";
    }

    @RequestMapping(value="/create_vol", method = RequestMethod.POST)
    public String showVolAfterCreate(@Valid @ModelAttribute("vol") FoundationVol vol) {
        saveVolunteer(vol);
        return "VolunteerMenagment/vol";
    }

    private void saveVolunteer(FoundationVol vol) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = auth.getName();
        User userExist = userService.findUserByEmail(currentUserName);
        vol.setFoundation_id(userExist.getFoundation().getFoundation_id());
        vol.setFoundation_name(userExist.getFoundation().getFoundation_name());
        User userExist2 = userService.findUserByEmail(vol.getVol_email());
        vol.setVol_id(userExist2.getVolunteer().getVolunteer_id());

        repo.save(vol);
    }

    private List<Foundation> listALLFun() {

        return funRepo.findAll();
    }
    private List <FoundationVol> findFun (List<FoundationVol> list, int id){
        list.removeIf(p->p.getVol_id()!= id);
        return list;
    }

    private List<FoundationVol> listALL() {
        return repo.findAll();
    }
    private  List<FoundationVol> find (List<FoundationVol> list, int id ){
        list.removeIf(p->p.getFoundation_id() != id);
        return list;
    }
}

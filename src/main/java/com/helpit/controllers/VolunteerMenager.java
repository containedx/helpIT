package com.helpit.controllers;

import com.helpit.model.*;
import com.helpit.repositories.*;
import com.helpit.services.UserService;

import com.helpit.model.Foundation;
import com.helpit.model.FoundationVol;
import com.helpit.model.User;

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
    @Autowired
    private VolRequestsRepository RequestRepo;
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
        int id = userExist.getFoundation().getId();
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
        int id = userExist.getVolunteer().getId();
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

    @GetMapping("/SendRequest")
    public String SendRequest(WebRequest request, Model model) {
        Vol_Requests vol_request = new Vol_Requests();
        model.addAttribute("vol_request", vol_request);
        return "VolunteerMenagment/SendRequest";
    }

    @RequestMapping(value="/create_request", method = RequestMethod.POST)
    public String CreateRequest(@Valid @ModelAttribute("vol_request") Vol_Requests vol_request) {
        saveRequest(vol_request);
        return "VolunteerMenagment/vol";
    }


    @RequestMapping("/checkRequests")
    public String checkRequests(Model model) {
        List <Vol_Requests> volRequests = RequestRepo.findAll();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = auth.getName();
        User userExist = userService.findUserByEmail(currentUserName);
        int id = userExist.getFoundation().getId();
        findRequest(volRequests,id);
        model.addAttribute("volRequests", volRequests);

        return "VolunteerMenagment/RequestList";
    }

    private List<Vol_Requests> findRequest(List<Vol_Requests> volRequests, int id) {
        volRequests.removeIf(p->p.getFoundation_id()!= id);
        return volRequests;
    }

    private void saveRequest(Vol_Requests vol_request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = auth.getName();
        User userExist = userService.findUserByEmail(currentUserName);
        vol_request.setVol_id(userExist.getVolunteer().getId());
        User userExist2 = userService.findUserByEmail(vol_request.getFoundation_email());
        vol_request.setFoundation_id(userExist2.getFoundation().getId());

        RequestRepo.save(vol_request);
    }



    private void saveVolunteer(FoundationVol vol) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = auth.getName();
        User userExist = userService.findUserByEmail(currentUserName);
        vol.setFoundation_id(userExist.getFoundation().getId());
        vol.setFoundation_name(userExist.getFoundation().getName());
        User userExist2 = userService.findUserByEmail(vol.getVol_email());
        vol.setVol_id(userExist2.getVolunteer().getId());

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

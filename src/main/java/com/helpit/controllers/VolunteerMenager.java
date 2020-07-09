package com.helpit.controllers;

import com.helpit.events.Event;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
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
        return "VolunteerManagement/vol";
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

        return "VolunteerManagement/vol";
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

        return "VolunteerManagement/vol";
    }



    @GetMapping("/SendRequest")
    public String SendRequest(WebRequest request, Model model) {
        Vol_Requests vol_request = new Vol_Requests();
        model.addAttribute("vol_request", vol_request);
        return "VolunteerManagement/SendRequest";
    }

    @RequestMapping(value="/create_request", method = RequestMethod.POST)
    public String CreateRequest(@Valid @ModelAttribute("vol_request") Vol_Requests vol_request, BindingResult result) {

        saveRequest(vol_request,result);
        return "VolunteerManagement/vol";
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

        return "VolunteerManagement/RequestList";



    }

    @RequestMapping("request/delete/{id}")
    public String deleteRequest(@Valid @ModelAttribute("Vol_Requests") Vol_Requests vol_requests) {
        RequestRepo.deleteById(vol_requests.getId());
        return "VolunteerManagement/RequestList";

    }

    @RequestMapping("vol/delete/{id}")
    public String deleteVol(@Valid @ModelAttribute("vol") FoundationVol foundationVol) {
        repo.deleteById(foundationVol.getId());
        return "VolunteerManagement/vol";

    }



    @RequestMapping("request/accept/{id}")
    public String acceptRequest(@PathVariable Integer id, Model model, @Valid @ModelAttribute("Vol_Requests") Vol_Requests vol_requests) {
        vol_requests = RequestRepo.getOne(id);
        FoundationVol vol = new FoundationVol();
        User userExist = userService.findUserByEmail(vol_requests.getFoundation_email());
        vol.setFoundation_id(userExist.getFoundation().getId());
        vol.setFoundation_name(userExist.getFoundation().getName());
        vol.setVol_email(vol_requests.getVol_email());
        User userExist2 = userService.findUserByEmail(vol_requests.getVol_email());
        vol.setVol_id(userExist2.getVolunteer().getId());
        vol.setVol_name(userExist2.getVolunteer().getName());
        vol.setVol_name(userExist2.getVolunteer().getSurname());
        saveVolunteer(vol);
        RequestRepo.deleteById(vol_requests.getId());
        return "VolunteerManagement/RequestList";

    }



    private List<Vol_Requests> findRequest(List<Vol_Requests> volRequests, int id) {
        volRequests.removeIf(p->p.getFoundation_id()!= id);
        return volRequests;
    }

    private void saveRequest(Vol_Requests vol_request, Errors errors) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = auth.getName();
        User userExist = userService.findUserByEmail(currentUserName);
        vol_request.setVol_id(userExist.getVolunteer().getId());
        User userExist2 = userService.findUserByEmail(vol_request.getFoundation_email());
        vol_request.setFoundation_id(userExist2.getFoundation().getId());
        List <FoundationVol> list = repo.findAll();
        list.removeIf(p->p.getFoundation_id()!= vol_request.getFoundation_id());
        list.removeIf(p->p.getVol_id()!= vol_request.getVol_id());
        if (list.isEmpty()) {
            RequestRepo.save(vol_request);
        }
        else
        {
            errors.rejectValue("vol_email", "error.userEmail.exists");
        }
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

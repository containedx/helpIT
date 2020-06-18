package com.helpit.posts.controllers;

import com.helpit.model.Foundation;
import com.helpit.repositories.FoundationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class FoundationController {
    private final FoundationRepository foundation_repository;

    public FoundationController(FoundationRepository foundation_repository) {
        this.foundation_repository = foundation_repository;
    }

    @RequestMapping({"/foundation/list", "/foundation/list.html"})
    public String getFoundations(Model model) {
        model.addAttribute("foundations", foundation_repository.findAll());
        return "/foundation/list";
    }

    @RequestMapping({"/foundation/{id}/show"})
    public String getFoundationSite(@PathVariable String id, Model model) {
        Optional<Foundation> foundation = foundation_repository.findById(Integer.valueOf(id));
        if(foundation.isPresent()){
            model.addAttribute("foundation", foundation.get());
        }
        else {
            throw new RuntimeException("Cannot display foundation, because it is not present in the database");
        }
        return "/foundation/show";
    }

    @RequestMapping({"/charity/show"})
    public String getCharityShow()
    {
        return "/charity/show_default";
    }

    @RequestMapping({"/charity/{id}/show"})
    public String getCharityShow(@PathVariable String id, Model model)
    {
        Optional<Foundation> foundation = foundation_repository.findById(Integer.valueOf(id));
        if(foundation.isPresent()){
            model.addAttribute("foundation", foundation.get());
            model.addAttribute("articles", foundation.get().getPost());
        }
        else {
            throw new RuntimeException("Cannot display foundation, because it is not present in the database");
        }
        return "/charity/show";
    }

    @RequestMapping({"/charity/events"})
    public String getCharityEvents()
    {
        return "/charity/events";
    }

    @RequestMapping({"/charity/sponsors"})
    public String getCharitySponsors()
    {
        return "/charity/sponsors";
    }

    @RequestMapping({"/charity/edit"})
    public String getCharityEdit()
    {
        return "/registrations/edit_charity";
    }

    @RequestMapping({"/charity/list"})
    public String getFoundList(Model model)
    {
        model.addAttribute("foundations", foundation_repository.findAll());
        return "/charity/list";
    }
}

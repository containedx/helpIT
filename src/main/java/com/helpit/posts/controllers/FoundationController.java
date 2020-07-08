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

    @RequestMapping({"/foundation/{id}/show"})
    public String getCharityShow(@PathVariable String id, Model model)
    {
        Optional<Foundation> foundation = foundationRepository.findById(Integer.valueOf(id));

        if(foundation.isPresent()){
            model.addAttribute("foundation", foundation.get());
            model.addAttribute("articles", foundation.get().getPost());
        }
        else {
            throw new RuntimeException("Cannot display foundation, because it is not present in the database");
        }

        return "/foundation/show";
    }

    @RequestMapping({"/charity/events"})
    public String getCharityEvents()
    {
        return "/charity/events";
    }


    @RequestMapping({"/charity/{id}/opinion"})
    public String getCharityOpinions(@PathVariable String id,
                                     Model model)
    {
        Optional<Foundation> foundation = foundationRepository.findById(Integer.valueOf(id));
        if(foundation.isPresent()){
            model.addAttribute("foundation", foundation.get());

            double sumOfRates = foundation.get().getComment().stream().map(comment -> comment.getRate()).mapToInt(Integer::intValue).sum();
            double rating = sumOfRates / (double)foundation.get().getComment().size(); //
            model.addAttribute("rating", rating);
        }
        else {
            throw new RuntimeException("Cannot display foundation, because it is not present in the database");
        }
        return "/charity/opinions";
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


    @RequestMapping({"/charities"})
    public String getFoundList(Model model)
    {
        model.addAttribute("foundations", foundationRepository.findAll());
        return "/charity/list";
    }

}

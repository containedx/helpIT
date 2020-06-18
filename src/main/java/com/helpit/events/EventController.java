package com.helpit.events;

import com.helpit.repositories.UserRepository;
import com.helpit.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.List;


@Controller
public class EventController {

    private final EventRepository repo;

    private final UserRepository userRepository;

    @Autowired
    public EventController (EventRepository repo, UserRepository userRepository) {
        this.repo = repo;
        this.userRepository=userRepository;
    }


    @RequestMapping(value="/events",method=RequestMethod.GET)
    public String returnEventView(WebRequest request, Model model) {
        List<Event> listEvents = listAll();
        model.addAttribute("listEvents", listEvents);
        return "events/eventList";
    }

    @GetMapping("/events/add")
    public String addEvent(WebRequest request, Model model) {
        Event event = new Event();
        model.addAttribute("event", event);
        return "events/add";
    }

    //don't change it to postmapping!
    @RequestMapping(value="events/create_event", method = RequestMethod.POST)
    public String showEventAfterCreate(@Valid @ModelAttribute("event") Event event) {
        saveEvent(event);
        return "events/show";
    }

    @RequestMapping("events/delete/{id}")
    public String deleteEvent(@Valid @ModelAttribute("event") Event event) {
        delete(event.getId());
        return "events/del";
    }

    @RequestMapping("/events/sign/{id}")
    public String signForEvent(@PathVariable Long id, Model model, @Valid @ModelAttribute("event") Event event){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = auth.getName();
        User user = userRepository.findByEmail(currentUserName);
        Event current_event = getEvent(id);
        current_event.getUsers().add(user);
        repo.save(current_event);
        model.addAttribute("event", current_event);
        model.addAttribute("user", user);
        return "events/sign";
    }

    public List<Event> listAll() {
        return repo.findAll();
    }

    public void saveEvent(Event event){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = auth.getName();
        User user = userRepository.findByEmail(currentUserName);
        event.setFoundation(user.getFoundation());
        repo.save(event);
    }

    public Event getEvent(Long id){
        return repo.findById(id).get();
    }

    public void delete(Long id){
        repo.deleteById(id);
    }


}

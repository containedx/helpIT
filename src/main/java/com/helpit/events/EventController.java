package com.helpit.events;

import com.helpit.model.Foundation;
import com.helpit.repositories.FoundationRepository;
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
import java.util.Optional;


@Controller
public class EventController {

    private final EventRepository repo;

    private final UserRepository userRepository;

    private final FoundationRepository foundationRepository;

    @Autowired
    public EventController (EventRepository repo, UserRepository userRepository, FoundationRepository foundationRepository) {
        this.repo = repo;
        this.userRepository=userRepository;
        this.foundationRepository=foundationRepository;
    }

    //all events
    @RequestMapping(value="/events",method=RequestMethod.GET)
    public String returnEventView(WebRequest request, Model model) {
        List<Event> listEvents = listAll();
        model.addAttribute("listEvents", listEvents);
        return "events/eventList";
    }

    @RequestMapping(value="/{foundation_id}/events",method=RequestMethod.GET)
    public String showFoundationEvents(@PathVariable int foundation_id, Model model) {
        User foundation = findFoundationById(foundation_id);
        List<Event> listEvents = listAll();
        listEvents = listSpecific(listEvents, foundation);
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
    public String signForEvent(@PathVariable Long id, Model model){
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

    public User findFoundationById(int id) {
        return userRepository.findById(id).get();
    }

    public List<Event> listSpecific(List<Event> list, User foundation) {
        list.removeIf(p->p.getFoundation()!=foundation);
        return list;
    }

    public void saveEvent(Event event){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = auth.getName();
        User user = userRepository.findByEmail(currentUserName);
        event.setFoundation(user);
        repo.save(event);
    }

    public Event getEvent(Long id){
        return repo.findById(id).get();
    }

    public void delete(Long id){
        repo.deleteById(id);
    }


}

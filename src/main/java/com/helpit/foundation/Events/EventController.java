package com.helpit.foundation.Events;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class EventController {

    @GetMapping("/events")
    public String view(Model model)
    {
        Event event = new Event();
        model.addAttribute("event", event);
        return "event_demo_view";
    }


    @RequestMapping(value="/create_event", method = RequestMethod.POST)
    public String create_event_submit(@Valid @ModelAttribute("event") Event event)
    {
        return "added_event";
    }
}

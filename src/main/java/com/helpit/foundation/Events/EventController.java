package com.helpit.foundation.Events;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EventController {

    @GetMapping("/events")
    public String view()
    {
        return "event_demo_view";
    }

    @GetMapping("/create_event")
    public String create_event(Model model)
    {
        model.addAttribute("event", new Event());
        return "event_demo_view";
    }

    @PostMapping("/create_event")
    public String create_event_submit(@ModelAttribute Event event)
    {
        return "added_event";
    }
}

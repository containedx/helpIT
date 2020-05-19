package com.helpit.foundation.Events;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventController {

    @GetMapping("/events")
    private String view()
    {
        return "event_demo_view";
    }
}

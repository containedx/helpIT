package com.helpit.events;

import com.helpit.model.Foundation;
import com.helpit.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EventsTest {

    @Autowired
    private EventController controller;

    @Autowired
    private EventRepository repository;

    @Test
    void saveEvent()
    {
        Event test_event = new Event();
        test_event.setName("Test Event");
        test_event.setDate("01.01.2000 00:00");
        test_event.setFoundation(new User());
        test_event.setDescription("typed description in here");

        controller.saveEvent(test_event);

        assertEquals(test_event, controller.getEvent(test_event.getId()));
    }

    @Test
    void deleteEvent()
    {
        Event test_event2 = new Event();
        test_event2.setName("Test Event");
        test_event2.setDate("01.01.2000 00:00");
        User example_foundation = new User();
        test_event2.setFoundation(example_foundation);
        test_event2.setDescription("typed description in here");

        controller.saveEvent(test_event2);

        assertEquals(test_event2, controller.getEvent(test_event2.getId()));



        long count = repository.count();

        controller.delete(test_event2.getId());

        assertEquals(count-1, repository.count());
    }

}

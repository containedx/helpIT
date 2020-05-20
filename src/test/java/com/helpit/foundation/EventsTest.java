package com.helpit.foundation;

import com.helpit.foundation.Events.*;
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
        test_event.setFoundation("Example Foundation");
        test_event.setDescription("typed description in here");

        controller.save(test_event);

        assertEquals(test_event, controller.get(test_event.getId()));
    }

    @Test
    void deleteEvent()
    {
        Event test_event2 = new Event();
        test_event2.setName("Test Event");
        test_event2.setDate("01.01.2000 00:00");
        test_event2.setFoundation("Example Foundation");
        test_event2.setDescription("typed description in here");

        controller.save(test_event2);

        assertEquals(test_event2, controller.get(test_event2.getId()));



        long count = repository.count();

        controller.delete(test_event2.getId());

        assertEquals(count-1, repository.count());
    }

}

package com.helpit.events;

import com.helpit.events.Event;
import com.helpit.model.Foundation;
import com.helpit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    public List<Event> findByFoundation(Foundation foundation);

}

package com.helpit.events;

import com.helpit.model.Foundation;
import com.helpit.model.User;
import lombok.Data;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="event_id")
    private Long id;

    @Column(name="event_name")
    private String name;

    @Column(name="event_date")
    private String date;

    @Column(name="event_description")
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="users_events", joinColumns = @JoinColumn(name="event_id"), inverseJoinColumns = @JoinColumn(name="user_id"))
    private Set<User> users = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name="foundation_event", joinColumns = @JoinColumn(name="event_id"), inverseJoinColumns = @JoinColumn(name="foundation_id"))
    private Foundation foundation;

}

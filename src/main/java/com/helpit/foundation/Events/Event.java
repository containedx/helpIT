package com.helpit.foundation.Events;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String date;

    private String foundation;

    private String description;


}

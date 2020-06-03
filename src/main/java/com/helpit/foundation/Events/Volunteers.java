package com.helpit.foundation.Events;


import lombok.Data;
import javax.persistence.*;


@Data
@Entity
public class Volunteers {

    @Id
    private Long event_id;

    private int user_id;

}

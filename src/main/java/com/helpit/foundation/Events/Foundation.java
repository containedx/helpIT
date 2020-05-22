package com.helpit.foundation.Events;


    import lombok.Data;

    import javax.persistence.*;
    import javax.validation.constraints.NotNull;

@Entity
@Data
public class Foundation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String foundation_name;

    private String foundation_owner_name;

    private String foundation_owner_surname;

}

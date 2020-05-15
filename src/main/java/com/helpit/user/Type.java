package com.helpit.user;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="types")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "type_id")
    private int id;

    @Column(name="type_name")
    @NotNull
    private String type;
}

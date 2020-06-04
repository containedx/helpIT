package com.helpit.user;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Data
@Table(name = "foundations")
public class Foundation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "foundation_id")
    private int id;

    @Column(name = "foundation_name")
    @NotNull
    @NotBlank
    private String name;

    @Column(name = "foundation_owner_name")
    @NotNull
    @NotBlank
    private String ownerName;

    @Column(name = "foundation_owner_surname")
    @NotNull
    @NotBlank
    private String ownerSurname;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "foundation_types", joinColumns = @JoinColumn(name = "foundation_id"), inverseJoinColumns = @JoinColumn(name = "type_id"))
    private Type type;



}

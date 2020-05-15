package com.helpit.user;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
@Table(name = "foundations")
public class Foundation {


    @Column(name = "foundation_name")
    @NotNull
    private String foundation_name;

    @Column(name = "foundation_owner_name")
    @NotNull
    private String foundation_owner_name;

    @Column(name = "foundation_owner_surname")
    @NotNull
    private String foundation_owner_surname;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="foundations_types", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="type_id"))
    private Set<Type> types;

    @OneToOne()

    User user;

}

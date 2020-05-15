package com.helpit.user;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column( name = "user_id")
    int id;

    @Column(name="user_email")
    @NotNull
    @Email
    String email;


    @Column(name="user_password")
    @NotNull
    String password;

    @Column(name="user_active")
    @NotNull
    int active;

    @OneToOne()
    @JoinTable(name="user_role", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="role_id"))
    Role role;

    @OneToOne()
    @JoinTable(name="user_address", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="address_id"))
    private Address address;




}

package com.helpit.user;


import com.helpit.repositories.FoundationVolRepository;
import lombok.Data;
import org.hibernate.SQLQuery;
import org.hibernate.SharedSessionContract;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table( name = "foundation_vol")
public class FoundationVol {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "foundation_id")
    private int foundation_id;
    @Column (name = "foundation_name")
    private String foundation_name;
    @Column(name = "vol_id")
    private int vol_id;

    @Column(name = "vol_name")
    private String vol_name;

    @Column(name = "vol_surname")
    private String vol_surname;

    @Column(name = "vol_email")
    private String vol_email;



    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="users_foundation_vol", joinColumns = @JoinColumn(name="volunteer_id"), inverseJoinColumns = @JoinColumn(name="user_id"))
    private Set<User> users = new HashSet<>();


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name="foundation_foundation_vol", joinColumns = @JoinColumn(name="fun_id"), inverseJoinColumns = @JoinColumn(name="foundation_id"))
    private Foundation foundation;
}

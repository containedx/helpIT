package com.helpit.user;


import com.helpit.repositories.FoundationVolRepository;
import lombok.Data;
import org.hibernate.SQLQuery;
import org.hibernate.SharedSessionContract;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = "vol_id")
    private int vol_id;

    @Column(name = "vol_name")
    private String vol_name;

    @Column(name = "vol_surname")
    private String vol_surname;

    @Column(name = "vol_email")
    private String vol_email;



/*
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name="volunteers", joinColumns = @JoinColumn(name="volunteer_id"))
    private Volunteer volunteer;


    @ManyToOne( cascade = CascadeType.ALL)
    @JoinTable(name="foundations", joinColumns = @JoinColumn(name="foundation_id"))
    private Foundation foundation;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name="users", joinColumns = @JoinColumn(name="user_id"))
    private User User;
*/

}

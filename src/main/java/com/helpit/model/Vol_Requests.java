package com.helpit.model;

import com.helpit.model.Foundation;
import com.helpit.model.Volunteer;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "vol_requests")
public class Vol_Requests
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "vol_description")
    private String vol_description;


    @Column(name = "foundation_foundation_id")
    private int foundation_id;

    @Column(name = "volunteer_volunteer_id")

    private int vol_id;


    @Column(name = "volunteer_volunteer_email")
    private String vol_email;


    @Column(name = "foundation_foundation_name")
    private String foundation_email;

}

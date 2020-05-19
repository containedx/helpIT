package com.helpit.user;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name ="addresses")
public class Address {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "address_id")
   private int id;

   @Column(name = "address_city")
   @NotNull
   private String city;

   @Column(name = "address_street")
   private String street;

   @Column(name = "address_postcode")
   private String postcode;

   @Column(name = "address_number_of_home")
   private String number_of_home;

   @Column(name = "address_number_of_flat")
   private int number_of_flat;

}

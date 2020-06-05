package com.helpit.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
   @NotBlank
   private String city;

   @Column(name = "address_street")
   @NotBlank
   private String street;

   @Column(name = "address_postcode")
   @NotBlank
   private String postcode;

   @Column(name = "address_number_of_home")
   @NotBlank
   private String numberOfHome;

   @Column(name = "address_number_of_flat")
   private int numberOfFlat;

}

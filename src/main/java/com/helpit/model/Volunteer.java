package com.helpit.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@Entity
@Table(name ="volunteers")
public class Volunteer{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "volunteer_id")
  private int id;

  @Column(name="volunteer_name")
  @NotNull
  @NotBlank
  private String name;

  @Column(name="volunteer_surname")
  @NotNull
  @NotBlank
  private String surname;




}

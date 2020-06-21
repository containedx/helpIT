package com.helpit.user;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;


@Data
@Entity
@Table(name ="volunteers")
public class Volunteer{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "volunteer_id")
  private int volunteer_id;

  @Column(name="volunteer_name")
  @NotNull
  private String volunteer_name;

  @Column(name="volunteer_surname")
  @NotNull
  private String volunteer_surname;



}

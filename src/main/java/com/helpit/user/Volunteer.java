package com.helpit.user;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Data
@Entity
@Table(name ="volunteers")
public class Volunteer{

  @Column(name="volunteer_name")
  @NotNull
  private String name;

  @Column(name="volunteer_surname")
  @NotNull
  private String surname;


  @OneToOne()
  User user;

}

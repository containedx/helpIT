package com.helpit.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "user_id")
    private int id;

    @Column(name="username")
    @NotNull
    @NotBlank
    private String username;

    @Column(name="email")
    @NotNull
    @NotBlank
    private String email;

    @Column(name="password")
    @NotNull
    @NotBlank
    private String password;

    @Transient
    private String confirmPassword;

    @Column(name="active")
    @NotNull
    private int active;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name="user_role", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="role_id"))
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name="user_address", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="address_id"))
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name="user_foundation", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="foundation_id"))
    private Foundation foundation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name="user_volunteer", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="volunteer_id"))
    private Volunteer volunteer;

    
    public String getFoundationName(){
        return foundation.getName();
    }
    
    public String getFoundationOwnerName(){
        return foundation.getOwnerName();
    }
    
    public String getFoundationOwnerSurname(){
        return foundation.getOwnerSurname();
    }

    public String getCity(){
        return address.getCity();
    }

    public String getPostcode(){
        return address.getPostcode();
    }

    public String getStreet(){
        return address.getStreet();
    }

    public String getNumberOfHome(){
        return address.getNumberOfHome();
    }

    public int getNumberOfFlat(){
        return address.getNumberOfFlat();
    }

    public String getVolunteerName(){
        return volunteer.getName();
    }
    public String getVolunteerSurname(){
        return volunteer.getSurname();
    }

    public void setFoundationName(String foundationName){
         foundation.setName(foundationName);
    }

    public void setFoundationOwnerName(String foundationOwnerName){
         foundation.setOwnerName(foundationOwnerName );
    }

    public void setFoundationOwnerSurname(String foundationOwnerSurname){
         foundation.setOwnerSurname(foundationOwnerSurname );
    }

    public void setCity(String city){
         address.setCity(city );
    }

    public void setPostcode(String postcode){
         address.setPostcode(postcode );
    }

    public void setStreet(String street){
         address.setStreet(street );
    }

    public void setNumberOfHome(String numberOfHome ){
         address.setNumberOfHome(numberOfHome );
    }

    public void setNumberOfFlat(int numberOfFlat){
         address.setNumberOfFlat(numberOfFlat );
    }
}

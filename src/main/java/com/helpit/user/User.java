package com.helpit.user;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "user_id")
    private int id;

    @Column(name="user_username")
    @NotNull
    private String username;

    @Column(name="user_email")
    @NotNull
    @Email
    private String email;

    @Column(name="user_password")
    @NotNull
    private String password;

    @Transient
    private String confirm_password;

    @Column(name="user_active")
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

    
    public String getFoundation_name(){
        return foundation.getFoundation_name();
    }
    
    public String getFoundation_owner_name(){
        return foundation.getFoundation_owner_name();
    }
    
    public String getFoundation_owner_surname(){
        return foundation.getFoundation_owner_surname();
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

    public String getNumber_of_home(){
        return address.getNumber_of_home();
    }

    public int getNumber_of_flat(){
        return address.getNumber_of_flat();
    }


    public void setFoundation_name(String foundation_name){
         foundation.setFoundation_name(foundation_name);
    }

    public void setFoundation_owner_name(String foundation_owner_name){
         foundation.setFoundation_owner_name(foundation_owner_name );
    }

    public void setFoundation_owner_surname(String foundation_owner_surname){
         foundation.setFoundation_owner_surname(foundation_owner_surname );
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

    public void setNumber_of_home(String number_of_home ){
         address.setNumber_of_home(number_of_home );
    }

    public void setNumber_of_flat(int number_of_flat){
         address.setNumber_of_flat(number_of_flat );
    }
}


package com.helpit.repositories;

import com.helpit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByEmail(String email);
    public User findByUsername(String username);
    List<User> findAllByRole_Role(String role);
    List<User> findAllByFoundation_NameContaining(String foundationName);
    List<User> findByFoundation_Type_Type(String type);
    List<User>findByAddress_City(String city);
    List<User>findAllByFoundation_NameContainingAndAddress_CityAndFoundation_Type_Type(String foundationName,String city,String type);

}
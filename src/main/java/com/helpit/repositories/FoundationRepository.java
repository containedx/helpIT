package com.helpit.repositories;

import com.helpit.user.Foundation;
import com.helpit.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoundationRepository extends JpaRepository<Foundation,Integer> {

}

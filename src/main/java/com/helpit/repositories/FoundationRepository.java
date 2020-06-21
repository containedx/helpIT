package com.helpit.repositories;


import com.helpit.model.Foundation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.helpit.model.Foundation;
import com.helpit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FoundationRepository extends JpaRepository<Foundation,Integer> {

}

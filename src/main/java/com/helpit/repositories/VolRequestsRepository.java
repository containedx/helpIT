package com.helpit.repositories;

import com.helpit.model.FoundationVol;
import com.helpit.model.Vol_Requests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolRequestsRepository extends JpaRepository<Vol_Requests,Integer> {
}

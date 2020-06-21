package com.helpit.repositories;

import com.helpit.model.FoundationVol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoundationVolRepository  extends JpaRepository<FoundationVol,Integer> {



}

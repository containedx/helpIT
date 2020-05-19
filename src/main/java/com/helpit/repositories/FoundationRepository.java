package com.helpit.repositories;

import com.helpit.user.Foundation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("foundationRepository")
public interface FoundationRepository extends JpaRepository<Foundation,Integer> {
}

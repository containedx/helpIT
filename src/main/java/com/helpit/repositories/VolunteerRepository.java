package com.helpit.repositories;

import com.helpit.user.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("volunteerRepository")
public interface VolunteerRepository extends JpaRepository<Volunteer,Integer> {

}

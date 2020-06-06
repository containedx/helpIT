package com.helpit.posts.repositories;

import com.helpit.posts.model.Volunteer;
import org.springframework.data.repository.CrudRepository;

public interface VolunteerRepository extends CrudRepository<Volunteer, Long> {
}

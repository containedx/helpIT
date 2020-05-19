package com.helpit.foundation.repositories;

import com.helpit.foundation.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
}

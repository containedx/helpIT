package com.helpit.repositories;

import com.helpit.user.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("typeRepository")
public interface TypeRepository extends JpaRepository<Type,Integer> {
    public Type findByType(String type_name);
}

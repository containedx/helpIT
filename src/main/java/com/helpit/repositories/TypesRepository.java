package com.helpit.repositories;

import com.helpit.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypesRepository extends JpaRepository<Type,Integer> {
    public Type findByType(String type_name);
}

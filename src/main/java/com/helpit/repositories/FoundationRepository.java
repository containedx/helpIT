package com.helpit.repositories;

<<<<<<< HEAD
import com.helpit.model.Foundation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

=======
import com.helpit.user.Foundation;
import com.helpit.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

>>>>>>> kamil-skorupa
@Repository
public interface FoundationRepository extends JpaRepository<Foundation,Integer> {

}

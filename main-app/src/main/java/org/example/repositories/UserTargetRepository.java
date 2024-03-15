package org.example.repositories;

import org.example.models.UserTarget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTargetRepository extends JpaRepository<UserTarget, Integer> {
    UserTarget findByUserIdAndProductId(Integer userId, Integer productId);

    List<UserTarget> findByUserId(Integer id);
}

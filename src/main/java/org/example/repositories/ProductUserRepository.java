package org.example.repositories;

import org.example.models.ProductUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductUserRepository extends JpaRepository<ProductUser, Integer> {
    ProductUser findByUserIdAndProductId(Integer userId, Integer productId);
    List<ProductUser> findByUserId(Integer id);
}

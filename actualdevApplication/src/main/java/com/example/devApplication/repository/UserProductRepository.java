package com.example.devApplication.repository;

import com.example.devApplication.entity.UserProduct;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserProductRepository extends JpaRepository<UserProduct, Long> {
    UserProduct findByName(String name);
    boolean existsByName(String name);
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("UPDATE UserProduct us SET us.quantity = ?2 WHERE us.id = ?1")
    void updateById(Long id, Double quantity);
}

package com.example.devApplication.repository;


import com.example.devApplication.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>{
    Product findByName(String name);

    boolean existsByName(String name);
    @Query(value = "SELECT * FROM product p WHERE p.name LIKE CONCAT('%',:param,'%')", nativeQuery = true)
    List<Product> searchProduct(@Param("param") String param);
}

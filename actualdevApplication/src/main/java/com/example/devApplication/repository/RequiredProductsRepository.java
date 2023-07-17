package com.example.devApplication.repository;

import com.example.devApplication.entity.RequiredProducts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequiredProductsRepository extends JpaRepository<RequiredProducts, Long> {

}

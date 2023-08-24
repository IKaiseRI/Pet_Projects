package com.example.devApplication.repository;

import com.example.devApplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
//ceva
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

}

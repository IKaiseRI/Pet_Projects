package com.example.devApplication.repository;

import com.example.devApplication.entity.Recipe;
import com.example.devApplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    //List<Recipe> findByUser(User user);
}

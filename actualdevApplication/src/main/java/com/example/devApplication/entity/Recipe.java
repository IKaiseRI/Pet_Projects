package com.example.devApplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "recipe_id"
    )
    private Set<RequiredProducts> requiredProducts = new HashSet<>();

    public Recipe(String name, Set<RequiredProducts> reqStock) {
        this.name = name;
        this.requiredProducts = reqStock;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", requiredProducts=" + requiredProducts +
                '}';
    }
}

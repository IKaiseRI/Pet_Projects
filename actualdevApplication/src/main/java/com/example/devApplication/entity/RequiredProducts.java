package com.example.devApplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="required_products")
public class RequiredProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requiredProductsID;
    private String name;
    private String quantity;

    public RequiredProducts(String name, String quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "RequiredProducts{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}

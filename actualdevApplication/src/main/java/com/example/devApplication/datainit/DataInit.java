package com.example.devApplication.datainit;

import com.example.devApplication.entity.*;
import com.example.devApplication.repository.RecipeRepository;
import com.example.devApplication.repository.RoleRepository;
import com.example.devApplication.repository.ProductRepository;
import com.example.devApplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInit implements CommandLineRunner {

    private final RecipeRepository recipeRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        Role user = new Role().setName("ROLE_USER");

        Role admin = new Role().setName("ROLE_ADMIN");

        user = roleRepository.save(user);
        admin = roleRepository.save(admin);


        List<Product> products = new ArrayList<>(
                List.of(
                        new Product("Chicken",238,18.4f,0,18.4f),
                        new Product("Beef",193,22.17f,0,7.07f),
                        new Product("Rise",303,7.5f,62.3f,2.6f),
                        new Product("Pasta",279,9.6f,60f,2.1f),
                        new Product("Chicken eggs",157,12.7f,0.7f,11.5f),
                        new Product("Champignon",27,4.3f ,0.1f,1f),
                        new Product("Tomatoes",18,0.9f,0.9f,0.2f),
                        new Product("Black pepper",251,10f,39f,3f),
                        new Product("Bacon",468,34f,1.7f,35.1f),
                        new Product("Potatoes",77,2f,16.3f,0.4f),
                        new Product("Onion",40,1.1f,9.3f,0.1f),
                        new Product("Broccoli",34,2.8f,6.6f,0.4f),
                        new Product("Parmesan",431,38f,4.1f,29f),
                        new Product("Feta cheese",264,14f,4.1f,21f),
                        new Product("Bell pepper",20,0.9f,4.6f,0.2f),
                        new Product("Cucumber",30,3f,6f,0f),
                        new Product("Olives",115,0.8f,6f,11f)
                )
        );

        productRepository.saveAll(products);

        List<UserProduct> userStocks1 = new ArrayList<>(
                List.of(
                        new UserProduct("Chicken", 5d),
                        new UserProduct("Pasta", 3d)
                )
        );



        List<UserProduct> userStocks2 = new ArrayList<>(
                List.of(
                        new UserProduct("Beef", 5d),
                        new UserProduct("Rise", 3d)
                )
        );

        Recipe recipe1 = new Recipe();
        recipe1.setRequiredProducts(
                Set.of(
                        new RequiredProducts("Pasta","2"),
                        new RequiredProducts("Bacon", "2"),
                        new RequiredProducts("Egg","2"))
                );
        recipe1.setName("Carbonara");

        Recipe recipe2 = new Recipe();
        recipe2.setRequiredProducts(
                Set.of(
                        new RequiredProducts("Chicken","2"))
        );
        recipe2.setName("Baked chicken");

        User appUser = new User()
                .setPassword(passwordEncoder.encode("123"))
                .setUsername("user")
                .withUserProducts(userStocks1)
                .withRecipes(List.of(recipe1,recipe2))
                .setRoles(Set.of(user));

        User appAdmin = new User()
                .setPassword(passwordEncoder.encode("123"))
                .setUsername("admin")
                .withUserProducts(userStocks2)
                .setRoles(Set.of(user,admin));

        userRepository.saveAll(Set.of(appUser,appAdmin));


    }

}

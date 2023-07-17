package com.example.devApplication.service;

import com.example.devApplication.entity.Recipe;
import com.example.devApplication.entity.RequiredProducts;
import com.example.devApplication.entity.User;
import com.example.devApplication.entity.UserProduct;
import com.example.devApplication.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final UserRepository userRepository;
    private final UserProductService userProductService;

    public List<Recipe> getRecipes(String username){
        User user = userRepository.findByUsername(username);
        return user.getRecipes();
    }
    public void createRecipe(RequiredProducts requiredProducts, String username){
        List<String> quantityList = new ArrayList<>(Arrays.stream(requiredProducts.getQuantity().split(",")).toList());
        List<String> productList = Arrays.stream(requiredProducts.getName().split(",")).toList();
        Recipe recipe = new Recipe();
        recipe.setName(quantityList.get(0));
        quantityList.remove(0);
        Set<RequiredProducts> requiredProductsSet = new HashSet<>();
        for (int i = 0; i < quantityList.size(); i++) {
            requiredProductsSet.add(new RequiredProducts(productList.get(i),quantityList.get(i)));
        }
        recipe.setRequiredProducts(requiredProductsSet);
        User user = userRepository.findByUsername(username);
        List<Recipe> usersRecipes = user.getRecipes();
        System.out.println(usersRecipes.toString());
        usersRecipes.add(recipe);
        userRepository.save(user.withRecipes(usersRecipes));
    }

    public Map<String,Double> getQuantities(String username){
        List<Recipe> listRecipe = userRepository.findByUsername(username).getRecipes();
        List<String> productNames = listRecipe.stream()
                .map(Recipe::getRequiredProducts)
                .flatMap(Collection::stream)
                .map(RequiredProducts::getName).toList();
        List<UserProduct> userProducts = userProductService.getUserProduct(username);
        Map<String,Double> newMap = userProducts.stream().collect(Collectors.toMap(UserProduct::getName, UserProduct::getQuantity));
        for (var name: productNames){
            if (!newMap.containsKey(name))
                newMap.put(name,0d);
        }
        return newMap;
    }

}

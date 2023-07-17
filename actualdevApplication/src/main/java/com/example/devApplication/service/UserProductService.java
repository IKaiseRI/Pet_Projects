package com.example.devApplication.service;

import com.example.devApplication.entity.DTO.UserProductDTO;
import com.example.devApplication.entity.User;
import com.example.devApplication.entity.UserProduct;
import com.example.devApplication.repository.ProductRepository;
import com.example.devApplication.repository.UserRepository;
import com.example.devApplication.repository.UserProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProductService {
    private final UserProductRepository userProductRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public List<UserProduct> getUserProduct(String name){
        return userRepository.findByUsername(name).getUserProducts();
    }

    public boolean createUserProduct(UserProductDTO userProduct, String username) throws Exception {
        User user = userRepository.findByUsername(username);
        List<UserProduct> stockList = user.getUserProducts();
        if(productRepository.existsByName(userProduct.getName())){
            if(stockList.stream().noneMatch(stock -> stock.getName().equals(userProduct.getName()))) {
                UserProduct oldUserProduct = stockList.stream()
                        .filter(stock -> stock.getName()
                                .equals(userProduct
                                        .getName()))
                        .findFirst().orElseThrow(() -> new Exception("Product not found"));
                userProductRepository.updateById(oldUserProduct.getId(), userProduct.getQuantity() + oldUserProduct.getQuantity());
            }else {
                stockList.add(mapToUserProduct(userProduct));
                userRepository.save(user.withUserProducts(stockList));
            }
            return true;
        }
        return false;
    }

    public UserProduct mapToUserProduct(UserProductDTO userProduct){
        UserProduct newUserProduct = new UserProduct();
        newUserProduct.setName(userProduct.getName());
        newUserProduct.setQuantity(userProduct.getQuantity());
        return newUserProduct;
    }


    public void deleteById(Long id){
        userProductRepository.deleteById(id);
    }

}

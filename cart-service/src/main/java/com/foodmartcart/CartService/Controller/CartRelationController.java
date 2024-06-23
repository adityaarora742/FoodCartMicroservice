package com.foodmartcart.CartService.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodmartcart.CartService.Entity.CartEntity;
import com.foodmartcart.CartService.Entity.Product;
import com.foodmartcart.CartService.Entity.User;
import com.foodmartcart.CartService.Entity.UserCartResponse;
import com.foodmartcart.CartService.Feign.ProductClient;
import com.foodmartcart.CartService.Feign.UserClient;
import com.foodmartcart.CartService.Repository.CartRepository;

@RestController
@RequestMapping("/carts")
public class CartRelationController {
	
	@Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private ProductClient productClient;
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserCartResponse> getUserCartDetails(@PathVariable("userId") Long id) {
        // Fetch user details
        User user = userClient.getUserById(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Fetch cart items for the user
        List<CartEntity> cartItems = cartRepository.findByUserId(id);
        List<Product> products = new ArrayList<>();

        for (CartEntity cart : cartItems) {
            Product product = productClient.getById(cart.getProductId());
            if (product != null) {
                products.add(product);
            }
        }

        // Create response
        UserCartResponse response = new UserCartResponse();
        response.setUser(user);
        response.setProducts(products);

        return ResponseEntity.ok(response);
    }

}

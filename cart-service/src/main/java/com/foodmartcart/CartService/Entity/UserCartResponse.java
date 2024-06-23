package com.foodmartcart.CartService.Entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCartResponse {
    private User user;
    private List<Product> products;
}


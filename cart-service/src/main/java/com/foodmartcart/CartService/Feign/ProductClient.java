package com.foodmartcart.CartService.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.foodmartcart.CartService.Entity.Product;

@FeignClient(name = "ProductService")
public interface ProductClient {
    @GetMapping("/products/{id}")
    Product getById(@PathVariable("id") Long id);
}

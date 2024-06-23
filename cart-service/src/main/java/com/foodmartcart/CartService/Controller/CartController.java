package com.foodmartcart.CartService.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.foodmartcart.CartService.Entity.CartEntity;
import com.foodmartcart.CartService.Repository.CartRepository;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartRepository cartRepository;
	
	
	
	@PostMapping
	public CartEntity create(@RequestBody CartEntity cartEntity) {
		return cartRepository.save(cartEntity);
	}
	
	@GetMapping("/{id}")
	public Optional<CartEntity> getById(@PathVariable Long id) {
		return cartRepository.findById(id);
	}
	
	@GetMapping
	public List<CartEntity> getAll(){
		return cartRepository.findAll();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CartEntity> delete(@PathVariable Long id){
		Optional<CartEntity> cart = cartRepository.findById(id);
		
		if(cart.isPresent()) {
			cartRepository.deleteById(id);
			return ResponseEntity.ok(cart.get());
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CartEntity> update(@PathVariable Long id, @RequestBody CartEntity cartEntity){
		Optional<CartEntity> cart = cartRepository.findById(id);
		CartEntity cart1 = cart.get();
		if(cart.isPresent()) {
			cart1.setQuantity(cartEntity.getQuantity());
			CartEntity updatedCart = cartRepository.save(cart1);
			return ResponseEntity.ok(updatedCart);
		}
		else {
			return ResponseEntity.notFound().build();
		}
		
	}
}

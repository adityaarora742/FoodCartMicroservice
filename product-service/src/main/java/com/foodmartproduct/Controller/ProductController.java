package com.foodmartproduct.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodmartproduct.Entity.ProductEntity;
import com.foodmartproduct.Repository.ProductRepository;


@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	
	@PostMapping
	public ProductEntity create(@RequestBody ProductEntity productEntity) {
		return productRepository.save(productEntity);
	}
	
	@GetMapping
	public List<ProductEntity> getAllList(){
		return productRepository.findAll();
		
	}
	
	@GetMapping("/{id}")
	public Optional<ProductEntity> getById(@PathVariable Long id){
		return productRepository.findById(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		Optional<ProductEntity> product = productRepository.findById(id);
		if(product.isPresent()) {
			productRepository.deleteById(id);
		}
		else {
			System.out.println("Id is not present");
		}
	}
	
	
}

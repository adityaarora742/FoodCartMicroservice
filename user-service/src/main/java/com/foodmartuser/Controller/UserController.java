package com.foodmartuser.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodmartuser.Entity.UserEntity;
import com.foodmartuser.Repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@PostMapping
	public UserEntity create(@RequestBody UserEntity userEntity) {
		return userRepository.save(userEntity);
	}
	
	@GetMapping("/{id}")
	public Optional<UserEntity> getUserById(@PathVariable Long id){
		return userRepository.findById(id);
	}
	
	@GetMapping
	public List<UserEntity> getAll(){
		return userRepository.findAll();
	}
	
	public ResponseEntity<UserEntity> deleteById(@PathVariable Long id){
		Optional<UserEntity> user = userRepository.findById(id);
		
		if(user.isPresent()) {
			userRepository.deleteById(id);
			return ResponseEntity.ok(user.get());
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}



















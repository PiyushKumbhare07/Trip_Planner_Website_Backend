package com.app.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.UserDTO;
import com.app.dto.UserDTOupdation;
import com.app.dto.UserPasswordDTO;
import com.app.dto.UserSignInDTO;
import com.app.entities.User;
import com.app.repository.UserInterface;
import com.app.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
@Validated
public class UserController {
	@Autowired
	private UserService uservice;
	@Autowired
	private ModelMapper mapper;
	
    @DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		return ResponseEntity.status(HttpStatus.OK).body(uservice.delete(id));
	}
    @PostMapping("/signin")
    public ResponseEntity<?> SignIn(@RequestBody @Valid UserSignInDTO user){
    	
    	return ResponseEntity.status(HttpStatus.OK).body(uservice.SignIn(user.getEmail(), user.getPassword()));
    }
     @PostMapping("/signup")
	public ResponseEntity<?> Create(@RequestBody @Valid UserDTO user) {
    	
         
		return ResponseEntity.status(HttpStatus.OK).body(uservice.Create(user));
	}
    
	@PutMapping("update/{id}")
	public ResponseEntity<?> update(@RequestBody @Valid UserDTOupdation user, @PathVariable long id) {
		
		return ResponseEntity.status(HttpStatus.OK).body(uservice.update(user, id));
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updatePassword(@RequestBody UserPasswordDTO user) {
		return ResponseEntity.status(HttpStatus.OK).body(uservice.updatePassword(user.getUserId(), user.getNewPassword()));
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable long id) {
		
		return ResponseEntity.status(HttpStatus.OK).body(uservice.get(id));
	}
}

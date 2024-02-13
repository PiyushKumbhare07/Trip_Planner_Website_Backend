package com.app.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.entities.Location;
import com.app.service.FlightService;

@RestController
@RequestMapping("/flight")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class FlightController {
	
@Autowired
private FlightService fs;

@GetMapping("/locations")
public ResponseEntity<?> getAllLocations() {
	
	return ResponseEntity.status(HttpStatus.OK).body(fs.getAllLocations());
	
}
@PostMapping(value = "/images/{id}", consumes = "multipart/form-data")
public ResponseEntity<?> uploadImage(@PathVariable Long id,@RequestParam MultipartFile imageFile) throws IOException{
	return ResponseEntity.status(HttpStatus.OK).body(fs.uploadImage(id, imageFile));
}

@GetMapping("/all")
public ResponseEntity<?> getAllFlights() {
	
	return ResponseEntity.status(HttpStatus.OK).body(fs.getAllFlights());
	
}
@GetMapping("/{to}/{from}/{dep}/{passengers}")
public ResponseEntity<?> getFlights(@PathVariable String to,@PathVariable String from,@PathVariable String dep,@PathVariable int passengers) {
	LocalDate dep1 = LocalDate.parse(dep);
	return ResponseEntity.status(HttpStatus.OK).body(fs.getFlights(to, from, dep1, passengers));
	
}
}

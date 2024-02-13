package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.HolidayService;

@RestController
@RequestMapping("/holiday")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class HolidayController {
	@Autowired
	private HolidayService hs;
@GetMapping("/{id}")
public ResponseEntity<?> getOnebyId(@PathVariable long id){
	return ResponseEntity.status(HttpStatus.OK).body(hs.getByID(id));
}
@GetMapping("/location/{location}")
public ResponseEntity<?> getOne(@PathVariable String location){
	return ResponseEntity.status(HttpStatus.OK).body(hs.getHolidayBylocation(location));
}
}

package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CreateBookingRequest;
import com.app.entities.Traveller;
import com.app.service.BookingService;
import com.app.service.FlightService;
import com.app.service.TicketService;
import com.app.service.UserService;

@RestController
@RequestMapping("/book")
@Validated
public class BookingController {
@Autowired
private BookingService bookingService;
@Autowired
private UserService  uService;
@Autowired
private TicketService tService;
@Autowired
private FlightService fService;
//list of travellers
	//flightID
	//return ->list of travellers,tickets,flight,bookingID
@PostMapping("/newbooking")
public ResponseEntity<?> createBooking(@RequestBody CreateBookingRequest request){
	return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.createBooking(request.getTravellers(), request.getUserID(), request.getFlightID()));
}
@GetMapping("/{id}")
public ResponseEntity<?> getBooking(@PathVariable long id){
	return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.getBooking(id));//checking github
}
@GetMapping("/user/{id}")
public ResponseEntity<?> getuserTickets(@PathVariable long id){
	return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.getTicketsForUser(id));
}

}

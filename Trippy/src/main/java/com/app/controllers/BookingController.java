package com.app.controllers;

import java.util.List;

import org.json.JSONObject;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CreateBookingRequest;
import com.app.dto.PayementDTO;
import com.app.entities.Bookings;
import com.app.entities.Orders;
import com.app.entities.Traveller;
import com.app.repository.OrdersRepo;
import com.app.service.BookingService;
import com.app.service.FlightService;
import com.app.service.TicketService;
import com.app.service.UserService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@RestController
@RequestMapping("/book")
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001"})
@Validated
public class BookingController {
@Autowired
private ModelMapper mapper;
@Autowired
private BookingService bookingService;
@Autowired
private OrdersRepo oRepo;
@Autowired
private UserService  uService;
@Autowired
private TicketService tService;
@Autowired
private FlightService fService;

@PostMapping("/newbooking")
public ResponseEntity<?> createBooking(@RequestBody CreateBookingRequest request){
	return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.createBooking(request.getTravellers(), request.getUserID(), request.getFlightID(),request.getType()));
}
@DeleteMapping("/cancel/{id}")
public ResponseEntity<?> cancelBooking(@PathVariable long id){
	return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.cancelBooking(id));
}

@GetMapping("/{id}")
public ResponseEntity<?> getBooking(@PathVariable long id){
	return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.getBooking(id));//checking github
}
@GetMapping("/userTickets/{id}")
public ResponseEntity<?> getuserTickets(@PathVariable long id){
	return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.getTicketsForUser(id));
}
@GetMapping("user/{id}")
public ResponseEntity<?> getUserBooking(@PathVariable long id){
	return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.getUserBookings(id));
}
@PostMapping("/order")
public boolean createOrder(@RequestBody PayementDTO p) throws RazorpayException{
	RazorpayClient client=new RazorpayClient("rzp_test_1Vn4YmQoYAJHMW","9i9qTrPg47LL3Q4lZr3FyLNQ");
	JSONObject obj=new JSONObject();
	obj.put("amount",(p.getAmount()*100));
	obj.put("currency", "INR");
    
	Order o=client.orders.create(obj);
	Orders saveOrder=new Orders();
	Bookings b=mapper.map(bookingService.getBooking(p.getBookingID()), Bookings.class);
	saveOrder.setBookingsID(b.getBookingID());
	saveOrder.setOrderID(o.get("id"));
	oRepo.save(saveOrder);
	System.out.println(o);
	if(oRepo.existsById(saveOrder.getId())) {
		return true;
	}
	else {
		return false;
	}
}
}

package com.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.ApiResponse;
import com.app.dto.BookingDTO;
import com.app.entities.Bookings;
import com.app.entities.Flight;
import com.app.entities.Ticket;
import com.app.entities.Traveller;
import com.app.entities.User;
import com.app.repository.BookingsRepo;
import com.app.repository.FlightRepo;
import com.app.repository.TicketsRepo;
import com.app.repository.TravellerRepo;
import com.app.repository.UserInterface;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {
    @Autowired 
    private BookingsRepo br;
    @Autowired
    private UserInterface ur;
    @Autowired
    private TicketsRepo tr;
    @Autowired
    private FlightRepo fr;
    @Autowired
    private TravellerRepo travRepo;
    @Autowired
    private ModelMapper mapper;
    //BookingDto
	@Override
	public BookingDTO createBooking(List<Traveller> travellers,long userID, long flightID) {
		Flight f=fr.findById(flightID)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid flight ID , Emp not found !!!!"));
		User u=ur.findById(userID)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid user ID , Emp not found !!!!"));
		Bookings booking=new Bookings(u,LocalDate.now());
		List<Ticket> tickets = new ArrayList<>();
	    for(Traveller trav:travellers) {
	    	Ticket t=new Ticket();
	    	t.setBooking(booking);
	    	t.setFlight(f);
	    	t.setDateOfPurchase(LocalDate.now());
	    	Random random = new Random();
	        int SNo = random.nextInt(f.getCapacity()) + 1;
	        t.setSeatNo(String.valueOf(SNo));
	        t.setPrice(f.getEconomyPrice());
	        trav.setTicket(t);
	        travRepo.save(trav);
	        t.setTraveller(trav);
	        tr.save(t);
	        tickets.add(t);
	    }
	   
	    booking.setTickets(tickets);
	    br.save(booking);
	    f.setCapacity(f.getCapacity()-travellers.size());
	   
//	    System.out.println(booking.getTickets());
//	    return new ApiResponse("created");
	    return mapper.map(booking, BookingDTO.class);
	   
	}
	@Override
	public BookingDTO getBooking(long id) {
		Bookings b=br.findById(id).orElseThrow(()->new ResourceNotFoundException("Invalid booking ID , Emp not found !!!!"));
		  
		return mapper.map(b, BookingDTO.class);
	}
	public List<Ticket> getTicketsForUser(long id){
		return ur.getTicketsByUserId(id);
	}

}

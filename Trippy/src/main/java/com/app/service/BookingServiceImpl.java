package com.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.ApiResponse;
import com.app.dto.BookingDTO;
import com.app.entities.Bookings;
import com.app.entities.Flight;
import com.app.entities.Holiday;
import com.app.entities.Ticket;
import com.app.entities.Traveller;
import com.app.entities.User;
import com.app.repository.BookingsRepo;
import com.app.repository.FlightRepo;
import com.app.repository.HolidayRepo;
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
    private HolidayRepo holidayRepo;
    @Autowired
    private ModelMapper mapper;
    //BookingDto
	@Override
	public BookingDTO createBooking(List<Traveller> travellers,long userID, long flightID,String type) {
		Flight f=fr.findById(flightID)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid flight ID , Emp not found !!!!"));
		User u=ur.findById(userID)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid user ID , Emp not found !!!!"));
		Bookings booking=new Bookings(u,LocalDate.now());
		List<Ticket> tickets = new ArrayList<>();
		double sum=0;
	    for(Traveller trav:travellers) {
	    	Ticket t=new Ticket();
	    	t.setBooking(booking);
	    	t.setFlight(f);
	    	t.setDateOfPurchase(LocalDate.now());
	    	t.setType(type);
	    	Random random = new Random();
	        int SNo = random.nextInt(f.getCapacity()) + 1;
	        t.setSeatNo(String.valueOf(SNo));
	        if(type.equals("Business")) {
	        	t.setPrice(f.getBusinessClassPrice());
	        	sum+=f.getBusinessClassPrice();
	        }else {
	        	t.setPrice(f.getEconomyPrice());
	        	sum+=f.getBusinessClassPrice();
	        }
	        booking.setPrice(sum);
	        trav.setTicket(t);
	        travRepo.save(trav);
	        t.setTraveller(trav);
	        tr.save(t);
	        tickets.add(t);
	    }
	   
	    booking.setTickets(tickets);
	    br.save(booking);
	    f.setCapacity(f.getCapacity()-travellers.size());
	   

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
	@Override
	public BookingDTO createHolidayBooking(long userID, long holidayID, List<Traveller> travellers,String type) {
		Holiday holiday=holidayRepo.findById(holidayID).orElseThrow(() -> new ResourceNotFoundException("Invalid holiday ID , Emp not found !!!!"));
		User u=ur.findById(userID)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid user ID , user not found !!!!"));
		Bookings booking=new Bookings(u,LocalDate.now());
		List<Ticket> tickets = new ArrayList<>();
	    for(Traveller trav:travellers) {
	    	Ticket t=new Ticket();
	    	t.setBooking(booking);
	    	t.setHoliday(holiday);
	    	t.setDateOfPurchase(LocalDate.now());
	    	Random random = new Random();
	       if(type=="Adult") {
	    	   t.setPrice(holiday.getPricePerAdult());
	       }else {
	    	   t.setPrice(holiday.getPricePerChild());
	       }
	        
	        trav.setTicket(t);
	        travRepo.save(trav);
	        t.setTraveller(trav);
	        tr.save(t);
	        tickets.add(t);
	    }
	   
	    booking.setTickets(tickets);
	    br.save(booking);
	    
	   

	    return mapper.map(booking, BookingDTO.class);
		
	}
	@Override
	public List<BookingDTO> getUserBookings(long id) {
		User u=ur.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid user ID , user not found !!!!"));
		List<Bookings> bookings=br.findByuser(u);
		
		return bookings.stream()
                .map(booking -> mapper.map(booking, BookingDTO.class))
                .collect(Collectors.toList());
	}
	@Override
	public ApiResponse cancelBooking(long id) {
		Bookings bookings=br.findById(id).orElseThrow(()->new ResourceNotFoundException("Invalid booking ID , Emp not found !!!!"));
		 for (Ticket ticket : bookings.getTickets()) {
		        ticket.setBooking(null); 
		    }

		 br.delete(bookings);
	    if (br.existsById(id)) {
	        return new ApiResponse("not deleted");
	    }
	    return new ApiResponse("deleted");
	}

}

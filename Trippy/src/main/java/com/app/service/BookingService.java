package com.app.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.app.dto.ApiResponse;
import com.app.dto.BookingDTO;
import com.app.entities.Ticket;
import com.app.entities.Traveller;

public interface BookingService {
	BookingDTO createBooking( List<Traveller> travellers,long userID, long flightID );
	BookingDTO getBooking(long id);
	 List<Ticket> getTicketsForUser(long id);
}
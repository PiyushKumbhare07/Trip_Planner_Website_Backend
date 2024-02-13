package com.app.dto;

import java.time.LocalDate;
import java.util.List;

import com.app.entities.Ticket;
import com.app.entities.Traveller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateBookingRequest {
	 private long userID;
	    private long flightID;
	    private List<Traveller> travellers;
}

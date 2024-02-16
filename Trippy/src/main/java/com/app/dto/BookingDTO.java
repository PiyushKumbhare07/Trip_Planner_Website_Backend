package com.app.dto;

import java.time.LocalDate;
import java.util.List;

import com.app.entities.Ticket;
import com.app.entities.Traveller;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookingDTO {
	 private long BookingID;
	    private LocalDate BookingDate;
	    private List<Ticket> tickets;
	    
}

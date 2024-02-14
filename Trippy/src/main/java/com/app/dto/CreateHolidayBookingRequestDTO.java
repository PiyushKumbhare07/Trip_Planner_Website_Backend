package com.app.dto;

import java.util.List;

import com.app.entities.Traveller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateHolidayBookingRequestDTO {
	private long userID;
    private long HolidayID;
    private List<Traveller> travellers;
}

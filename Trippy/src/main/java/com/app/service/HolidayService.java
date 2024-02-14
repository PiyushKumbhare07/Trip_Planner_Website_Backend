package com.app.service;

import java.util.List;

import com.app.dto.BookingDTO;
import com.app.entities.Holiday;
import com.app.entities.Traveller;


public interface HolidayService {
List<Holiday> getAll();
List<Holiday> getHolidayBylocation(String name);
Holiday getByID(long id);

}

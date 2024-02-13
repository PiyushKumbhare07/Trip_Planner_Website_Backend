package com.app.service;

import java.util.List;

import com.app.entities.Holiday;


public interface HolidayService {
List<Holiday> getAll();
List<Holiday> getHolidayBylocation(String name);
Holiday getByID(long id);
}

package com.app.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.ApiResponse;
import com.app.entities.Flight;
import com.app.entities.Location;




public interface FlightService {
List<Location> getAllLocations();
Location getByID(long id);
ApiResponse uploadImage(Long empId, MultipartFile image) throws IOException;
List<Flight> getAllFlights();
List<Flight> getFlights(String to,String from,LocalDate dep,int passengers);
Flight getSingleFlight(long id);
}

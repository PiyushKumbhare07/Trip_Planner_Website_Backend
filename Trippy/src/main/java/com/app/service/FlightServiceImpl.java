package com.app.service;

import java.io.Console;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.ApiResponse;
import com.app.entities.Flight;
import com.app.entities.Location;
import com.app.repository.FlightRepo;
import com.app.repository.LocationRepo;


@Service
@Transactional
public class FlightServiceImpl implements FlightService {
    @Autowired
	private LocationRepo lr;
    @Autowired
    private FlightRepo fr;
	@Override
	public List<Location> getAllLocations() {
		
		return lr.findAll();
	}
	
	public ApiResponse uploadImage(Long id, MultipartFile image) throws IOException {
		
		Location lc = lr.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Emo ID , Emp not found !!!!"));
		
		lc.setImageBlob(image.getBytes());
		return new ApiResponse("Image file uploaded successfully for emp id " + id);
	}

	@Override
	public Location getByID(long id) throws ResourceNotFoundException {
		Location lc = lr.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Emo ID , Emp not found !!!!"));
		return lc;
	}

	@Override
	public List<Flight> getAllFlights() {
		
		return fr.findAll();
	}

	@Override
	public List<Flight> getFlights(String to,String from,LocalDate dep,int passengers) {
		
		
		return fr.getFlights(to, from, passengers, dep);
	}

	@Override
	public Flight getSingleFlight(long id) {
		Flight flight=fr.findById(id).orElseThrow(()->new ResourceNotFoundException("flight not found"));
		return flight;
	}

	@Override
	public List<Flight> getAllToDestination(String dest) {
	
		return fr.findBytoArrival(dest);
	}

	
	
	

}

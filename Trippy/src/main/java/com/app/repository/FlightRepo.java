package com.app.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.Flight;

public interface FlightRepo extends JpaRepository<Flight, Long> {
	@Query("SELECT f FROM Flight f WHERE f.toArrival = :to AND f.fromDestination = :from AND f.Capacity > :passengers AND f.Departure = :dep")
	List<Flight> getFlights(@Param("to")String to,@Param("from")String from,@Param("passengers")int passengers,@Param("dep")LocalDate dep);
	List<Flight> findBytoArrival(String dest);
}

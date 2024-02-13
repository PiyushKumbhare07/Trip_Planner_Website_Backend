package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Bookings;

public interface BookingsRepo extends JpaRepository<Bookings, Long> {

}

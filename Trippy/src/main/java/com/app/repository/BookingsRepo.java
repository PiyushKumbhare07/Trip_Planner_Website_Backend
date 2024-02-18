package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.Bookings;
import com.app.entities.User;

public interface BookingsRepo extends JpaRepository<Bookings, Long> {
List<Bookings> findByuser(User u);


}

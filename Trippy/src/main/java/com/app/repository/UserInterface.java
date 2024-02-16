package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.Bookings;
import com.app.entities.Ticket;
import com.app.entities.User;


public interface UserInterface extends JpaRepository<User, Long>{
	@Query("SELECT b.tickets FROM Bookings b WHERE b.user.UserID = :id")
	List<Ticket> getTicketsByUserId(@Param("id") long id);
    User findByEmail(String email);
    boolean existsByEmail(String email);

}

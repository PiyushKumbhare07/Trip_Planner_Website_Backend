package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Ticket;

public interface TicketsRepo extends JpaRepository<Ticket, Long> {

}

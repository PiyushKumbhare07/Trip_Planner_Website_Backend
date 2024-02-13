package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Traveller;

public interface TravellerRepo extends JpaRepository<Traveller, Long> {

}

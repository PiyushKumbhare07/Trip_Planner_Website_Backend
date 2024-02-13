package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Location;

public interface LocationRepo extends JpaRepository<Location, Long> {

}

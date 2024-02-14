package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Holiday;

public interface HolidayRepo extends JpaRepository<Holiday, Long> {
List<Holiday> findBylocation(String name);
}

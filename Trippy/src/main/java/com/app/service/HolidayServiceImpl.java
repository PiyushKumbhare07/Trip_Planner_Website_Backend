package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.entities.Holiday;
import com.app.repository.HolidayRepo;

@Service
@Transactional
public class HolidayServiceImpl implements HolidayService {

	@Autowired
	private HolidayRepo hr;
	@Override
	public List<Holiday> getAll() {
		
		return hr.findAll();
	}

	@Override
	public List<Holiday> getHolidayBylocation(String name) {
		
		return hr.findBylocation(name);
	}

	@Override
	public Holiday getByID(long id) {
		Holiday holiday=hr.findById(id).orElseThrow(()->new ResourceNotFoundException("Holiday id not found"));
		return null;
	}

}

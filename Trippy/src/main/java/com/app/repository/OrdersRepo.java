package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Orders;

public interface OrdersRepo extends JpaRepository<Orders,Long > {
	Orders findByOrderID(String OrderID);
}

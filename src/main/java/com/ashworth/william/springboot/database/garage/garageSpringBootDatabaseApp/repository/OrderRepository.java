package com.ashworth.william.springboot.database.garage.garageSpringBootDatabaseApp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashworth.william.springboot.database.garage.garageSpringBootDatabaseApp.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>
{
	Page<Order> findByVehicleId(Long vehicleId, Pageable pageable);
	
//	Page<Order> findByOrderId(Long orderId, pageable);
}


package com.ashworth.william.springboot.database.garage.garageSpringBootDatabaseApp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashworth.william.springboot.database.garage.garageSpringBootDatabaseApp.exception.ResourceNotFoundException;
import com.ashworth.william.springboot.database.garage.garageSpringBootDatabaseApp.model.*;
import com.ashworth.william.springboot.database.garage.garageSpringBootDatabaseApp.repository.*;


@RestController
@RequestMapping("/api")
public class VehicleController
{
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private GarageSpringBootRepository garageSpringBootRepository;
	
	@GetMapping("/vehicle/{vehicleId}/orders")
	public Page<Order> getAllOrdersByVehicleId(@PathVariable ("vehicleId")Long vehicleId, Pageable pageable)
	{
		return orderRepository.findByVehicleId(vehicleId, pageable);
	}
	
	@GetMapping("/vehicle/{vehicleId}/orders/{orderId}")
	public Page<Order> getAnOrderByOrderId(@PathVariable ("vehicleId")Long vehicleId, @PathVariable ("orderId")Long orderId, Pageable pageable)
	{
		return orderRepository.findById(orderId);
	}
	
	@PostMapping("/vehicle/{vehicleId}/orders")
	public Order createComment(@PathVariable ("vehicleId")Long vehicleId, @Valid @RequestBody Order order)
	{
		return garageSpringBootRepository.findById(vehicleId)
				.map(garageSpringBootModel ->
				{
					order.setVehicle(garageSpringBootModel);
					return orderRepository.save(order);
				}).orElseThrow(()-> new ResourceNotFoundException("Vehicle", "id", order));
	}

	@PutMapping("/vehicle/{vehicleId}/orders/{orderId}")
	public Order updateOrder(@PathVariable ("vehicleId")Long vehicleId, @PathVariable ("orderId") Long orderId, @Valid @RequestBody Order orderRequest)
	{
		if(!garageSpringBootRepository.existsById(vehicleId))
		{
			throw new ResourceNotFoundException("Vehicle", "id", orderRequest);
		}
		
		return orderRepository.findById(orderId).map(order -> 
			{
				order.setTitle(orderRequest.getTitle());
				return orderRepository.save(order);
			}).orElseThrow(() -> new ResourceNotFoundException("orderId", "id", orderRequest));
	}
	
	@DeleteMapping("/vehicle/{vehicleId}/orders/{orderId}")
	public ResponseEntity<?> deleteComment(@PathVariable ("vehicleId")Long vehicleId, @PathVariable ("orderId")Long orderId)
	{
		if(!garageSpringBootRepository.existsById(vehicleId))
		{
			throw new ResourceNotFoundException("Vehicle", "id", vehicleId);
		}
		
		return orderRepository.findById(orderId).map(order ->
		{
			orderRepository.delete(order);
			return ResponseEntity.ok().build();
		}).orElseThrow(()-> new ResourceNotFoundException("orderId", orderId.toString(), null));
	}
}

package com.ashworth.william.springboot.database.garage.garageSpringBootDatabaseApp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.ashworth.william.springboot.database.garage.garageSpringBootDatabaseApp.model.GarageSpringBootModel;
import com.ashworth.william.springboot.database.garage.garageSpringBootDatabaseApp.repository.GarageSpringBootRepository;

@RestController
@RequestMapping("/api")
public class GarageSpringBootController 
{
	@Autowired
	GarageSpringBootRepository garageRepository;
	
	//Method to create a vehicle
	@PostMapping("/vehicle")
	public GarageSpringBootModel createVehicle(@Valid @ RequestBody GarageSpringBootModel gSDM)
	{
		return garageRepository.save(gSDM);
	}
	
	//Method to get a vehicle by id
	@GetMapping("/vehicle/{id}")
	public GarageSpringBootModel getVehicleID(@PathVariable("id")Long vehicleID)
	{
		return garageRepository.findById(vehicleID).orElseThrow(()-> new ResourceNotFoundException("GarageSpringBootModel", "id", vehicleID));
	}
	
	//Method to get a vehicle by type
	@GetMapping("/vehicle/type/{type}")
	public List<GarageSpringBootModel> getVehicleType(@PathVariable(value ="type")String vehicleType)
	{	
		if(garageRepository.findByType(vehicleType) == null)
		{
			throw new ResourceNotFoundException("GarageSpringBootModel", "type", vehicleType);
		}
		
		return garageRepository.findByType(vehicleType);
		
	}

	//Method to get all vehicle
	@GetMapping("/vehicle")
	public List<GarageSpringBootModel> getAllVehicles()
	{
		return garageRepository.findAll();
	}
 
	//Method to update a vehicle
	@PutMapping("/vehicle/{id}")
	public GarageSpringBootModel updateVehicle(@PathVariable("id")Long vehicleID, @Valid @RequestBody GarageSpringBootModel vehicleDetails)
	{
		GarageSpringBootModel gSDM = garageRepository.findById(vehicleID).orElseThrow(()-> new ResourceNotFoundException("GarageSpringBootModel", "id", vehicleID));
		 
		gSDM.setType(vehicleDetails.getType());
		gSDM.setRegNumber(vehicleDetails.getRegNumber());
		gSDM.setManufacturer(vehicleDetails.getManufacturer());
		gSDM.setMake(vehicleDetails.getMake());
		gSDM.setColour(vehicleDetails.getColour());
		gSDM.setTopSpeed(vehicleDetails.getTopSpeed());
		
		GarageSpringBootModel updateData = garageRepository.save(gSDM);
		return updateData;
	}
	
	//Method to remove a vehicle
	@DeleteMapping("/vehicle/{id}")
	public ResponseEntity<?> deleteVehicle(@PathVariable("id")Long vehicleID)
	{
		GarageSpringBootModel gSDM = garageRepository.findById(vehicleID).orElseThrow(()-> new ResourceNotFoundException("GarageSpringBootModel", "id", vehicleID));
	
		garageRepository.delete(gSDM);
		return ResponseEntity.ok().build();
	}
}


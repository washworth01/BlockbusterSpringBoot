package com.ashworth.william.springboot.database.blockbuster.blockbusterSpringBootDatabaseApp.controller;

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

import com.ashworth.william.springboot.database.blockbuster.blockbusterSpringBootDatabaseApp.exception.ResourceNotFoundException;
import com.ashworth.william.springboot.database.blockbuster.blockbusterSpringBootDatabaseApp.model.BlockbusterSpringBootModel;
import com.ashworth.william.springboot.database.blockbuster.blockbusterSpringBootDatabaseApp.repository.BlockbusterSpringBootRepository;

@RestController
@RequestMapping("/api")
public class BlockbusterSpringBootController 
{
	@Autowired
	BlockbusterSpringBootRepository blockbusterRepository;
	
	//Method to create a vehicle
	@PostMapping("/film")
	public BlockbusterSpringBootModel createFilm(@Valid @ RequestBody BlockbusterSpringBootModel bSDM)
	{
		return blockbusterRepository.save(bSDM);
	}
	
	//Method to get a vehicle by id
	@GetMapping("/film/{id}")
	public BlockbusterSpringBootModel getFilmID(@PathVariable("id")Long filmID)
	{
		return blockbusterRepository.findById(filmID).orElseThrow(()-> new ResourceNotFoundException("BlockbusterSpringBootModel", "id", filmID));
	}
	
	//Method to get a vehicle by type
	@GetMapping("/film/title/{title}")
	public List<BlockbusterSpringBootModel> getFilmTitle(@PathVariable(value ="title")String filmTitle)
	{	
		if(blockbusterRepository.findByTitle(filmTitle) == null)
		{
			throw new ResourceNotFoundException("BlockbusterSpringBootModel", "title", filmTitle);
		}
		
		return blockbusterRepository.findByTitle(filmTitle);
		
	}
	//Method to get a film by category
	@GetMapping("/film/category/{category}")
	public List<BlockbusterSpringBootModel> getFilmCategory(@PathVariable(value ="category")String filmCategory)
	{
		if(blockbusterRepository.findByCategory(filmCategory) == null)
		{
			throw new ResourceNotFoundException("BlockbusterSpringBootModel", "category", filmCategory);
		}
		
		return blockbusterRepository.findByCategory(filmCategory);
	}
	
	//Method to get a film by rating
	@GetMapping("/film/rating/{rating}")
	public List<BlockbusterSpringBootModel> getFilmRating(@PathVariable(value ="rating")String filmRating)
	{
		if(blockbusterRepository.findByRating(filmRating) == null)
		{
			throw new ResourceNotFoundException("BlockbusterSpringBootModel", "category", filmRating);
		}
		
		return blockbusterRepository.findByRating(filmRating);
	}

	//Method to get all vehicle
	@GetMapping("/film")
	public List<BlockbusterSpringBootModel> getAllFilm()
	{
		return blockbusterRepository.findAll();
	}
 
	//Method to update a vehicle
	@PutMapping("/film/{id}")
	public BlockbusterSpringBootModel updateFilm(@PathVariable("id")Long filmID, @Valid @RequestBody BlockbusterSpringBootModel filmDetails)
	{
		BlockbusterSpringBootModel bSDM = blockbusterRepository.findById(filmID).orElseThrow(()-> new ResourceNotFoundException("BlockbusterSpringBootModel", "id", filmID));
		 
		bSDM.setTitle(filmDetails.getTitle());
		bSDM.setDescription(filmDetails.getDescription());
		bSDM.setCategory(filmDetails.getCategory());
		bSDM.setPrice(filmDetails.getPrice());
		bSDM.setLength(filmDetails.getLength());
		bSDM.setRating(filmDetails.getRating());
		
		BlockbusterSpringBootModel updateData = blockbusterRepository.save(bSDM);
		return updateData;
	}
	
	//Method to remove a vehicle
	@DeleteMapping("/film/{id}")
	public ResponseEntity<?> deleteVehicle(@PathVariable("id")Long vehicleID)
	{
		BlockbusterSpringBootModel bSDM = blockbusterRepository.findById(vehicleID).orElseThrow(()-> new ResourceNotFoundException("BlockbusterSpringBootModel", "id", vehicleID));
	
		blockbusterRepository.delete(bSDM);
		return ResponseEntity.ok().build();
	}
}


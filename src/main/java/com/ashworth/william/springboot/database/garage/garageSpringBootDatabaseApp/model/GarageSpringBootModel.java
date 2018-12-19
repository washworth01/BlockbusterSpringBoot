package com.ashworth.william.springboot.database.garage.garageSpringBootDatabaseApp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "vehicle")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"creationDate", "lastModified"}, allowGetters = true)
public class GarageSpringBootModel implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String type;
	
	@NotBlank
	private String regNumber;
	
	@NotBlank
	private String manufacturer;
	
	@NotBlank
	private String make;
	
	private String colour;
	
	private int topSpeed;
	
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date creationDate;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date lastModified;

	public GarageSpringBootModel()
	{
		
	}
		
	public GarageSpringBootModel(String type, String regNumber, String manufacturer, String make, String colour, int topSpeed)
	{
		this.type = type;
		this.regNumber = regNumber;
		this.manufacturer = manufacturer;
		this.make = make;
		this.colour = colour;
		this.topSpeed = topSpeed;
	}
	
	public Long getId() 
	{
		return id;
	}

	public String getType() 
	{
		return type;
	}

	public void setType(String type) 
	{
		this.type = type;
	}

	public String getRegNumber()
	{
		return regNumber;
	}

	public void setRegNumber(String regNumber) 
	{
		this.regNumber = regNumber;
	}

	public String getManufacturer()
	{
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) 
	{
		this.manufacturer = manufacturer;
	}

	public String getMake() 
	{
		return make;
	}

	public void setMake(String make) 
	{
		this.make = make;
	}

	public String getColour()
	{
		return colour;
	}

	public void setColour(String colour)
	{
		this.colour = colour;
	}

	public int getTopSpeed() 
	{
		return topSpeed;
	}

	public void setTopSpeed(int topSpeed) {
		this.topSpeed = topSpeed;
	}

	public Date getCreationDate()
	{
		return creationDate;
	}

	public Date getLastModified() 
	{
		return lastModified;
	}
	
}

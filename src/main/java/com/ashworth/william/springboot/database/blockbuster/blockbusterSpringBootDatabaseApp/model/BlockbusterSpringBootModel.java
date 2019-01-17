package com.ashworth.william.springboot.database.blockbuster.blockbusterSpringBootDatabaseApp.model;

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
@Table(name = "film_list")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"creationDate", "lastModified"}, allowGetters = true)
public class BlockbusterSpringBootModel implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "FID") private Long id;
	
	@NotBlank
	@Column (name = "title") private String title;
	
	@NotBlank
	@Column (name = "description") private String description;
	
	@NotBlank
	@Column (name = "category") private String category;
	
	@NotBlank
	@Column (name = "price") private float price;
	
	@Column (name = "length") private int length;
	
	@NotBlank
	@Column (name = "rating") private String rating;
	
	@Column (name = "actors") private String actors;

	public BlockbusterSpringBootModel()
	{
		
	}
		
	public BlockbusterSpringBootModel(String title, String description, String category, float price, int length, String rating, String actors)
	{
		this.title = title;
		this.description = description;
		this.category = category;
		this.price = price;
		this.length = length;
		this.rating = rating;
		this.actors = actors;
	}

	public String getActors()
	{
		return actors;
	}

	public void setActors(String actors)
	{
		this.actors = actors;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public float getPrice()
	{
		return price;
	}

	public void setPrice(float price)
	{
		this.price = price;
	}

	public int getLength()
	{
		return length;
	}

	public void setLength(int length)
	{
		this.length = length;
	}

	public String getRating()
	{
		return rating;
	}

	public void setRating(String rating)
	{
		this.rating = rating;
	}
	
}
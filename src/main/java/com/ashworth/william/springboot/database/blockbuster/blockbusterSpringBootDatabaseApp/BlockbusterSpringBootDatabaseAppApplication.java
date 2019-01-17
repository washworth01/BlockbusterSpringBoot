package com.ashworth.william.springboot.database.blockbuster.blockbusterSpringBootDatabaseApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BlockbusterSpringBootDatabaseAppApplication 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(BlockbusterSpringBootDatabaseAppApplication.class, args);
	}
}

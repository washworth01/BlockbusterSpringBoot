package com.ashworth.william.springboot.database.blockbuster.blockbusterSpringBootDatabaseApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashworth.william.springboot.database.blockbuster.blockbusterSpringBootDatabaseApp.model.BlockbusterSpringBootModel;

@Repository
public interface BlockbusterSpringBootRepository extends JpaRepository<BlockbusterSpringBootModel, Long>
{
	List<BlockbusterSpringBootModel> findByTitle(String filmTitle);
	List<BlockbusterSpringBootModel> findByCategory(String filmCategory);
	List<BlockbusterSpringBootModel> findByRating(String filmRating);
}

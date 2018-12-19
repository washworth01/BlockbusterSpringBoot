package repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.ashworth.william.springboot.database.garage.garageSpringBootDatabaseApp.GarageSpringBootDatabaseAppApplication;
import com.ashworth.william.springboot.database.garage.garageSpringBootDatabaseApp.model.GarageSpringBootModel;
import com.ashworth.william.springboot.database.garage.garageSpringBootDatabaseApp.repository.GarageSpringBootRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {GarageSpringBootDatabaseAppApplication.class})
@DataJpaTest
public class RepositoryTest
{
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private GarageSpringBootRepository garageRepo;
	
	@Test
	public void retrieveByIdTest()
	{
		GarageSpringBootModel model1 = new GarageSpringBootModel("Car","AH58 QXZ","Honda","Civic","Brown",100);
		entityManager.persist(model1);
		entityManager.flush();
		assertTrue(garageRepo.findById(model1.getId()).isPresent());
	}
	
	@Test
	public void findByType()
	{
		List<GarageSpringBootModel> vehicles;
		GarageSpringBootModel model2 = new GarageSpringBootModel("Car","AH58 QXZ","Honda","Civic","Brown",100);
		entityManager.persist(model2);
		entityManager.flush();
		vehicles = garageRepo.findByType("Car");
		for(GarageSpringBootModel g : vehicles)
		{
			assertEquals(g.getType(), "Car");
		}
		
	}
}

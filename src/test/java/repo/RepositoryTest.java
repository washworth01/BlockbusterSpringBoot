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

import com.ashworth.william.springboot.database.blockbuster.blockbusterSpringBootDatabaseApp.BlockbusterSpringBootDatabaseAppApplication;
import com.ashworth.william.springboot.database.blockbuster.blockbusterSpringBootDatabaseApp.model.BlockbusterSpringBootModel;
import com.ashworth.william.springboot.database.blockbuster.blockbusterSpringBootDatabaseApp.repository.BlockbusterSpringBootRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {BlockbusterSpringBootDatabaseAppApplication.class})
@DataJpaTest
public class RepositoryTest
{
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private BlockbusterSpringBootRepository garageRepo;
	
	@Test
	public void retrieveByIdTest()
	{
		BlockbusterSpringBootModel model1 = new BlockbusterSpringBootModel("Car","AH58 QXZ","Honda","Civic","Brown",100);
		entityManager.persist(model1);
		entityManager.flush();
		assertTrue(garageRepo.findById(model1.getId()).isPresent());
	}
	
	@Test
	public void findByType()
	{
		List<BlockbusterSpringBootModel> vehicles;
		BlockbusterSpringBootModel model2 = new BlockbusterSpringBootModel("Car","AH58 QXZ","Honda","Civic","Brown",100);
		entityManager.persist(model2);
		entityManager.flush();
		vehicles = garageRepo.findByType("Car");
		for(BlockbusterSpringBootModel g : vehicles)
		{
			assertEquals(g.getType(), "Car");
		}
		
	}
}

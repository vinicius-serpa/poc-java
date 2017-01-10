package dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
 
import java.util.Calendar;
import java.util.List;
 
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
 
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.CarDAO;
import dao.helper.DbUnitHelper;
import model.Car;

public class CarTest {

	private static DbUnitHelper dbUnitHelper;
	private static EntityManagerFactory factory;
	
	private EntityManager manager;
	private CarDAO carDAO;
 
	@BeforeClass
	public static void initClass() {
		dbUnitHelper = new DbUnitHelper("DbUnitXml");
		factory = Persistence.createEntityManagerFactory("artigoTesteIntegracaoDbunitPU");
	}
 
	@Before
	public void init() {
		dbUnitHelper.execute(DatabaseOperation.DELETE_ALL, "Car.xml");
 
		dbUnitHelper.execute(DatabaseOperation.INSERT, "Car.xml");
 
		manager = factory.createEntityManager();
		this.carDAO = new CarDAO(manager);
	}
	
	@After
	public void end() {
		this.manager.close();
	}
 
	@Test
	public void deveRetornarCarrosZeroKm() {
		List<Car> resultado = carDAO.buscarCarrosZero();
		
		assertThat(resultado, hasItems(new Car(1L), new Car(4L)));
	}
 
	@Test
	public void deveRetornarCarrosMenosDoisAnosUso() {
		Integer doisAnosAntes = Calendar.getInstance().get(Calendar.YEAR) - 2;
		List<Car> resultado = carDAO.buscarCarrosComIdadeInferiorA(doisAnosAntes);
		
		assertThat(resultado, hasItems(new Car(1L), new Car(2L), new Car(3L), new Car(4L)));
	}

}

package so.aman.mappedSupperClass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import so.aman.mappedSupperClass.db.EmployeeEntity;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private EmployeeService service;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		EmployeeEntity entity = new EmployeeEntity();
		entity.setEmail("aa@so.com");
		entity.setFirstName("First name");
		entity.setLastName("Last name");

		service.saveEmployee(entity);
		
		EmployeeEntity entity2 = new EmployeeEntity();
		entity2.setEmail("bb@so.com");
		entity2.setFirstName("First name");
		entity2.setLastName("Last name");
		
		service.saveEmployee(entity2);
		
		EmployeeEntity entity3 = new EmployeeEntity();
		entity3.setEmail("bb@so.com");
		entity3.setFirstName("3");
		entity3.setLastName("Last name");
		
		service.saveEmployee(entity3);

		
		Page<EmployeeEntity> page = service.getAll(PageRequest.of(0, 10));
		page.getContent().stream().forEach(System.out::println);
	}

}

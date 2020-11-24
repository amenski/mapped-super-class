package so.aman.mappedSupperClass;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import so.aman.mappedSupperClass.db.EmployeeEntity;
import so.aman.mappedSupperClass.db.EmployeeEntity2;
import so.aman.mappedSupperClass.db.EmployeeRepository2;
import so.aman.mappedSupperClass.db.Item2;
import so.aman.mappedSupperClass.db.ItemRepository2;

@SpringBootApplication
@Transactional
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private EmployeeService service;
	
	@Autowired
	ItemRepository2 itemRepo;
	
	@Autowired
	public EmployeeRepository2 empRepository2;

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
		
		EmployeeEntity2 entity3 = new EmployeeEntity2();
		entity3.setEmail("bb@so.com");
		entity3.setFirstName("3");
		entity3.setLastName("Last name");
		
		
		entity3 = empRepository2.save(entity3);

		Item2 item = new Item2();
		item.setDeleted(false);
		item.setEmployee(entity3);
		
		itemRepo.saveAndFlush(item);
		
//		Page<EmployeeEntity> page = service.getAll(PageRequest.of(0, 10));
//		page.getContent().stream().forEach(System.out::println);
		
//		System.out.println(Arrays.asList(service.getDistinctResult()));
//		System.out.println(Arrays.asList(itemRepo.findAll()));
		List<EmployeeEntity2> list = empRepository2.findUserWithNonDeletedItems();
		list.stream().forEach(val -> System.out.println(!val.getItems().isEmpty() ? "size: -------" + val.getItems() : "Hi hi: " + val.getFirstName()));
//		System.out.println(Arrays.asList(list));
	}

}

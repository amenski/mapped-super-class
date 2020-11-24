package so.aman.mappedSupperClass;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import so.aman.mappedSupperClass.db.EmployeeEntity;
import so.aman.mappedSupperClass.db.EmployeeRepository;

@Service
public class EmployeeService {

	public EmployeeRepository empRepository;

	public EmployeeService(EmployeeRepository repository) {
		this.empRepository = repository;
	}

	@Transactional(rollbackFor = Exception.class)
	public EmployeeEntity saveEmployee(EmployeeEntity entity) {
		return empRepository.save(entity);
	}

	public Page<EmployeeEntity> getAll(Pageable pageable) {
		Specification<EmployeeEntity> specification = (Specification<EmployeeEntity>) (root, query, builder) -> null;
		return empRepository.findAll(specification, pageable);
	}
	
	public List<String> getDistinctResult() {
		return empRepository.selectDistinct();
	}
}
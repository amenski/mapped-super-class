package so.aman.mappedSupperClass.db;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends CommonRepository<EmployeeEntity> {

	@Query(value = "SELECT DISTINCT email FROM TBL_EMPLOYEES", nativeQuery = true)
	List<String> selectDistinct();
	
}

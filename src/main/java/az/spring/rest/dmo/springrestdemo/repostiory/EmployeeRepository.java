package az.spring.rest.dmo.springrestdemo.repostiory;

import az.spring.rest.dmo.springrestdemo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findByNameAndSurname(String name,String surname);
}

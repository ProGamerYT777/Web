package ru.skypro.lessons.springboot.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.web.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.web.model.Employee;

import java.util.List;
import java.util.Optional;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT SUM(e.salary) from  Employee e")
    double salarySum();
    @Query("SELECT AVG(e.salary) from  Employee e")
    int employeeHighSalary();

    @Query("SELECT new ru.skypro.lessons.springboot.web.dto.EmployeeDTO(e.id, e.name, e.salary, p.position) " +
            "FROM Employee  e left join Position p on p.id = e.position.id " +
            "where  e.salary = (SELECT MIN(e.salary) from  Employee e)")
    Optional<EmployeeDTO> minSalary();
    @Query("SELECT new ru.skypro.lessons.springboot.web.dto.EmployeeDTO(e.id, e.name, e.salary, p.position) " +
            "FROM Employee  e left join Position p on p.id = e.position.id " +
            "where e.salary = (SELECT MAX(e.salary) from  Employee e)")
    List<EmployeeDTO> maxSalary();

    List<Employee> findEmployeeBySalaryIsGreaterThan(Integer salary);
    List<Employee> findEmployeeByPosition_Position(String position);

}

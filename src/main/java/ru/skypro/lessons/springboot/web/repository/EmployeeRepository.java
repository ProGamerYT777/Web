package ru.skypro.lessons.springboot.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.web.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.web.model.Employee;
import ru.skypro.lessons.springboot.web.model.EmployeeFullInfo;
import ru.skypro.lessons.springboot.web.model.Position;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT SUM(e.salary) from  Employee e")
    double salarySum();
    @Query("SELECT new ru.skypro.lessons.springboot.web.dto.EmployeeDTO(e.id, e.name, e.salary, p.position) " +
            "FROM Employee  e left join Position p on p.id = e.position.id " +
            "where  e.salary = (SELECT MIN(e.salary) from  Employee e)")
    Optional<Employee> minSalary();
    @Query("SELECT new ru.skypro.lessons.springboot.web.dto.EmployeeDTO(e.id, e.name, e.salary, p.position) " +
            "FROM Employee  e left join Position p on p.id = e.position.id " +
            "where e.salary = (SELECT MAX(e.salary) from  Employee e)")
    List<Employee> maxSalary();
    @Query("SELECT AVG(e.salary) from  Employee e")
    int employeeHighSalary();
    void createEmployee(Employee employee);
    void updateEmployeeById(Employee employee);
    Optional<Employee> getEmployeeById(Integer id);
    void deleteById(Integer id);
    List<Employee> findEmployeeBySalaryIsGreaterThan(Integer salary);
    @Query(value = "SELECT * " +
            "FROM Employee e " +
            "WHERE salary = (SELECT MAX(salary) FROM Employee e2)",
            nativeQuery = true)
    List<Employee> getEmployeeWithHighestSalary();
    List<Employee> findEmployeeByPosition_Position(String position);
    @Query("SELECT e.name , e.salary , p.name " +
            "FROM Employee e join fetch Position p " +
            "WHERE e.id = ?1 and e.position = p")
    List<EmployeeFullInfo> getFullInfo();
    void upload(MultipartFile file) throws IOException;

}

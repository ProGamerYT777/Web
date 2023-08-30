package ru.skypro.lessons.springboot.web.repository;

import io.github.classgraph.Resource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.web.model.Employee;
import ru.skypro.lessons.springboot.web.model.EmployeeFullInfo;
import ru.skypro.lessons.springboot.web.model.Position;
import ru.skypro.lessons.springboot.web.model.Report;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    double sumSalariesEmployees();
    Employee minSalaryEmployee();
    Employee maxSalaryEmployee();
    List<Employee> highAverageSalariesEmployees();
    void createEmployee(Employee employee);
    void updateEmployeeById(Employee employee);
    Optional<Employee> getEmployeeById(Integer id);
    void deleteById(Integer id);
    List<Employee> employeesSalaryHighThan(int salary);
    @Query(value = "SELECT * " +
            "FROM Employee e " +
            "WHERE salary = (SELECT MAX(salary) FROM Employee e2)",
             nativeQuery = true)
    List<Employee> getEmployeeWithHighestSalary();
    List<Employee> getEmployeesByPositionLike(Position position);

    @Query("SELECT e.name , e.salary , p.name " +
            "FROM Employee e join fetch Position p " +
            "WHERE e.id = ?1 and e.position = p")
    List<EmployeeFullInfo> getFullInfo(Integer id);
    void upload(MultipartFile file) throws IOException;
    @Query(value = "CREATE TABLE report (" +
            "departmentName STRING NOT NULL," +
            "quantityEmployees INTEGER NOT NULL" +
            "maxSalary INTEGER NOT NULL" +
            "minSalary INTEGER NOT NULL" +
            "averageSalary INTEGER NOT NULL)",
            nativeQuery = true)
    String reportToFile(Report report);
    ResponseEntity<Resource> downloadFile(ByteArrayResource report);

}

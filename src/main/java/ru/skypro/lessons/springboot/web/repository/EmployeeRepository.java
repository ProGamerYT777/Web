package ru.skypro.lessons.springboot.web.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.skypro.lessons.springboot.web.model.Employee;
import ru.skypro.lessons.springboot.web.model.EmployeeFullInfo;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    double sumSalariesEmployees();
    Employee minSalaryEmployee();
    Employee maxSalaryEmployee();
    List<Employee> highAverageSalariesEmployees();
    void createEmployee(Employee employee);
    void updateEmployeeById(Employee employee);
    Employee getEmployeeById(Integer id);
    void deleteById(Integer id);
    List<Employee> employeesSalaryHighThan(int salary);
    List<Employee> getEmployeeWithHighestSalary();
    List<Employee> getEmployeesByPositionLike(String position);

    @Query("SELECT new ru.skypro.lessons.springboot.web.model.EmployeeFullInfo." +
            "EmployeeFullInfo(e.name , e.salary , p.name) " +
            "FROM Employee e join fetch Position p " +
            "WHERE e.position = p")
    List<EmployeeFullInfo> getFullInfo(Integer id);
    List<Employee> getPageInfo(int pageIndex, int unitPerPage);
}

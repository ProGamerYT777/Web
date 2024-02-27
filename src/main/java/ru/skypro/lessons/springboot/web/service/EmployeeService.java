package ru.skypro.lessons.springboot.web.service;

import io.github.classgraph.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.web.model.Employee;
import ru.skypro.lessons.springboot.web.model.EmployeeFullInfo;
import ru.skypro.lessons.springboot.web.model.Position;
import ru.skypro.lessons.springboot.web.model.Report;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    double sumSalariesEmployees();
    Employee minSalaryEmployee();
    Employee maxSalaryEmployee();
    Integer highAverageSalariesEmployees();
    void createEmployee(Employee employee);
    void updateEmployeeById(Employee employee);
    Optional<Employee> getEmployeeById(Integer id);
    void deleteById(Integer id);
    List<Employee> employeesSalaryHighThan(Integer salary);
    List<Employee> getEmployeeWithHighestSalary();
    List<Employee> getEmployeesByPositionLike(String position);
    List<EmployeeFullInfo> getFullInfo(Integer id);
    List<Employee> getPageInfo(int pageIndex, int unitPerPage);
    void upload(MultipartFile file) throws IOException;

}

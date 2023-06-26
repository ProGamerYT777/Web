package ru.skypro.lessons.springboot.web.service;

import org.springframework.data.repository.CrudRepository;
import ru.skypro.lessons.springboot.web.model.Employee;
import ru.skypro.lessons.springboot.web.model.EmployeeFullInfo;

import java.util.List;

public interface EmployeeService extends CrudRepository<Employee, Integer> {
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
    List<EmployeeFullInfo> getFullInfo(Integer id);
    List<Employee> getPageInfo(int pageIndex, int unitPerPage);

}

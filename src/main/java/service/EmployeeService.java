package service;

import ru.skypro.lessons.springboot.web.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> sumSalariesEmployees();
    List<Employee> minSalaryEmployee();
    List<Employee> maxSalaryEmployee();
    List<Employee> highAverageSalariesEmployees();

}

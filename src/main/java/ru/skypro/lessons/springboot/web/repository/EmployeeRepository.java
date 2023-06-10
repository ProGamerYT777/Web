package ru.skypro.lessons.springboot.web.repository;

import ru.skypro.lessons.springboot.web.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> sumSalariesEmployees();
    List<Employee> minSalaryEmployee();
    List<Employee> maxSalaryEmployee();
    List<Employee> highAverageSalariesEmployees();
}

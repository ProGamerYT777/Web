package ru.skypro.lessons.springboot.web.repository;

import ru.skypro.lessons.springboot.web.Employee;

import java.util.List;

public interface EmployeeRepository {
    double sumSalariesEmployees();
    List<Employee> minSalaryEmployee();
    List<Employee> maxSalaryEmployee();
    List<Employee> highAverageSalariesEmployees();
}

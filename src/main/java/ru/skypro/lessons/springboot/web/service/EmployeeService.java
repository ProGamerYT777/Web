package ru.skypro.lessons.springboot.web.service;

import ru.skypro.lessons.springboot.web.Employee;

import java.util.List;

public interface EmployeeService {
    double sumSalariesEmployees();
    Employee minSalaryEmployee();
    Employee maxSalaryEmployee();
    List<Employee> highAverageSalariesEmployees();

}

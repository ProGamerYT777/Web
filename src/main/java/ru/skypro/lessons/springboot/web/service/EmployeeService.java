package ru.skypro.lessons.springboot.web.service;

import ru.skypro.lessons.springboot.web.Employee;

import java.util.List;

public interface EmployeeService {
    double sumSalariesEmployees();
    Employee minSalaryEmployee();
    Employee maxSalaryEmployee();
    List<Employee> highAverageSalariesEmployees();
    void createEmployee(Employee employee);
    void updateEmployeeById(int id);
    Employee employeeGetById(int id);
    void deleteById(int id);
    List<Employee> EmployeesSalaryHighThan(double salary);

}

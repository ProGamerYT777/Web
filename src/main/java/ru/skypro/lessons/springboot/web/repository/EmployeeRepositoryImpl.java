package ru.skypro.lessons.springboot.web.repository;

import ru.skypro.lessons.springboot.web.Employee;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository{
    private final List<Employee> employeeList = List.of(
            new Employee("Ivan", 30000),
            new Employee("Oleg", 20000),
            new Employee("Olga", 25000),
            new Employee("Anton", 40000));

    @Override
    public double sumSalariesEmployees() {
        return employeeList.stream().mapToDouble(Employee::getSalary).sum();
    }

    @Override
    public List<Employee> minSalaryEmployee() {
        return employeeList;
    }

    @Override
    public List<Employee> maxSalaryEmployee() {
        return employeeList;
    }

    @Override
    public List<Employee> highAverageSalariesEmployees() {
        return employeeList;
    }
}

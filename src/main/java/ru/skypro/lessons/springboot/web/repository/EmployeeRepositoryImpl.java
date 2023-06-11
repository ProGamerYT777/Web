package ru.skypro.lessons.springboot.web.repository;

import ru.skypro.lessons.springboot.web.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

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
    public Employee minSalaryEmployee() {
       return employeeList.stream().min(Comparator.comparing(Employee::getSalary)).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Employee maxSalaryEmployee() {
        return employeeList.stream().max(Comparator.comparing(Employee::getSalary)).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public double highAverageSalariesEmployees() {
        return sumSalariesEmployees() / employeeList.size();
    }
}

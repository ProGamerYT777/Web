package ru.skypro.lessons.springboot.web.repository;

import ru.skypro.lessons.springboot.web.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final List<Employee> employeeList = List.of(

            new Employee("Ivan", 30000, 1),
            new Employee("Oleg", 20000, 2),
            new Employee("Olga", 25000, 3),
            new Employee("Anton", 40000, 4));

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
    public List<Employee> highAverageSalariesEmployees() {
        double averageSallary = sumSalariesEmployees() / employeeList.size();
            return employeeList.stream().filter (e -> e.getSalary() >= averageSallary).collect (Collectors.toList());
    }

    @Override
    public void createEmployee(Employee employee) {
        employeeList.add(employee);
    }

    @Override
    public void updateEmployeeById(Employee employee) {
        Employee oldEmployee = getEmployeeById(employee.getId());
        if (oldEmployee != null) {
            oldEmployee.setName(employee.getName());
            oldEmployee.setSalary(employee.getSalary());
        }
    }

    @Override
    public Employee getEmployeeById(int id) {
        try {
            return employeeList.stream().filter(employee -> employee.getId() == id).findFirst().orElseThrow();
        } catch (Exception e) {
            System.out.println("Нет сотрудника с id: " + id);
            return null;
        }
    }

    @Override
    public void deleteById(int id) {
        employeeList.remove(getEmployeeById(id));
    }

    @Override
    public List<Employee> employeesSalaryHighThan(double salary) {
        return employeeList.stream().filter(i -> i.getSalary() > salary).toList();
    }
}

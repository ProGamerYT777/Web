package ru.skypro.lessons.springboot.web.repository;

import ru.skypro.lessons.springboot.web.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
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
    public void updateEmployeeById(int id) {

    }

    @Override
    public Employee employeeGetById(int id) {
        try {
            return employeeList.stream().filter(employee -> employee.getId() == id).findFirst().orElseThrow();
        } catch (Exception e) {
            System.out.println("Нет сотрудника с id: " + id);
            return null;
        }
    }

    @Override
    public void deleteById(int id) {
        employeeList.remove(employeeGetById(id));
    }

    @Override
    public List<Employee> EmployeesSalaryHighThan(double salary) {
        return null;
    }
}

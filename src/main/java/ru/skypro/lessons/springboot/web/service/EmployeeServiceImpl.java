package ru.skypro.lessons.springboot.web.service;

import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.web.Employee;
import ru.skypro.lessons.springboot.web.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public double sumSalariesEmployees() {
        return employeeRepository.sumSalariesEmployees();
    }

    @Override
    public Employee minSalaryEmployee() {
        return employeeRepository.minSalaryEmployee();
    }

    @Override
    public Employee maxSalaryEmployee() {
        return employeeRepository.maxSalaryEmployee();
    }

    @Override
    public List<Employee> highAverageSalariesEmployees() {
        return employeeRepository.highAverageSalariesEmployees();
    }

    @Override
    public void createEmployee(Employee employee) {
        employeeRepository.createEmployee(employee);
    }
    @Override
    public void updateEmployeeById(Employee employee) {
        employeeRepository.updateEmployeeById(employee);
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeRepository.getEmployeeById(id);
    }

    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> employeesSalaryHighThan(double salary) {
        return employeeRepository.employeesSalaryHighThan(salary);
    }
}


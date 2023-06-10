package ru.skypro.lessons.springboot.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.lessons.springboot.web.Employee;
import service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/salary/sum")
        public double showSumSalaries() {
            return employeeService.sumSalariesEmployees();
        }

    @GetMapping("/salary/min")
        public List<Employee> showMinSalary() {
            return employeeService.minSalaryEmployee();
    }

    @GetMapping("/salary/max")
        public List<Employee> showMaxSalary() {
            return employeeService.maxSalaryEmployee();
    }

    @GetMapping("/high-salary")
        public List<Employee> showHighAverageSalaries() {
            return employeeService.highAverageSalariesEmployees();
    }
    }


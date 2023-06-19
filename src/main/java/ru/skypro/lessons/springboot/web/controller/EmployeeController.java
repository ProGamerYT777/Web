package ru.skypro.lessons.springboot.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.web.Employee;
import ru.skypro.lessons.springboot.web.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/salary/sum")
        public double showSumSalaries() {
            return employeeService.sumSalariesEmployees();
        }

    @GetMapping("/salary/min")
        public Employee showMinSalary() {
            return employeeService.minSalaryEmployee();
    }

    @GetMapping("/salary/max")
        public Employee showMaxSalary() {
            return employeeService.maxSalaryEmployee();
    }

    @GetMapping("/high-salary")
        public List<Employee> showHighAverageSalaries() {
            return employeeService.highAverageSalariesEmployees();
    }
    @PostMapping("/")
        public void createEmployee(@RequestBody Employee employee) {
            employeeService.createEmployee(employee);
    }
    @PutMapping("/{id}")
        public void updateEmployeeById(@PathVariable("id") int id) {
            employeeService.updateEmployeeById(id);
    }

    @GetMapping("/{id}")
        public Employee employeeGetById(@PathVariable("id") int id) {
            return employeeService.employeeGetById(id);
    }
    @DeleteMapping("/{id}")
        public void deleteById(@PathVariable("id") int id) {
            employeeService.deleteById(id);
    }
    @GetMapping("/salaryHigherThan?salary=")
        public List<Employee> showEmployeesSalaryHighThan(@RequestParam("salary") double salary) {
           return employeeService.EmployeesSalaryHighThan(salary);
    }
}


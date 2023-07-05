package ru.skypro.lessons.springboot.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.web.model.Employee;
import ru.skypro.lessons.springboot.web.model.EmployeeFullInfo;
import ru.skypro.lessons.springboot.web.model.Position;
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
        public void updateEmployeeById(@PathVariable("id") @RequestBody Employee employee) {
            employeeService.updateEmployeeById(employee);
    }
    @GetMapping("/{id}")
        public Employee getEmployeeById(@PathVariable("id") Integer id) {
            return employeeService.getEmployeeById(id);
    }
    @DeleteMapping("/{id}")
        public void deleteById(@PathVariable("id") Integer id) {
            employeeService.deleteById(id);
    }
    @GetMapping("/salaryHigherThan?salary=")
        public List<Employee> showEmployeesSalaryHighThan(@RequestParam("salary") int salary) {
           return employeeService.employeesSalaryHighThan(salary);
    }
    @GetMapping("/withHighestSalary")
        public List<Employee> showEmployeeWithHighestSalary() {
            return employeeService.getEmployeeWithHighestSalary();
    }
    @GetMapping
        public List<Employee> getEmployeesByPositionLike(@RequestParam("position") Position position) {
            return employeeService.getEmployeesByPositionLike(position);
    }
    @GetMapping("/{id}/fullInfo")
        public List<EmployeeFullInfo> getFullInfo(@PathVariable("id") Integer id) {
            return employeeService.getFullInfo(id);
    }
    @GetMapping("/page")
        public List<Employee> getPageInfo(@RequestParam ("page") int pageIndex, int unitPerPage) {
            return employeeService.getPageInfo(pageIndex, unitPerPage);
    }
}


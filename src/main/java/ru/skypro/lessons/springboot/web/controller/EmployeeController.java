package ru.skypro.lessons.springboot.web.controller;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.web.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.web.model.Employee;
import ru.skypro.lessons.springboot.web.model.EmployeeFullInfo;
import ru.skypro.lessons.springboot.web.model.Position;
import ru.skypro.lessons.springboot.web.service.EmployeeService;

import java.io.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/salary/sum")
    public double getSalarySum() {
        return employeeService.salarySum();
    }

    @GetMapping("/salary/min")
    public EmployeeDTO getSalaryMin() {
        return employeeService.minSalary();
    }

    @GetMapping("/salary/max")
    public EmployeeDTO getSalaryMax() {
        return employeeService.maxSalary();
    }

    @GetMapping("/salary/high-salary")
    public Integer getEmployeeHighSalary() {
        return employeeService.employeeHighSalary();
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/all-employee-new")
    public List<EmployeeDTO> all() {
        return employeeService.getAllNew();
    }

    @GetMapping("withHighestSalary")
    public List<EmployeeDTO> salaryWithHighestSalary() {
        return employeeService.withHighestSalary();
    }
    @GetMapping
    public List<EmployeeDTO> getEmployeesForPosition(@RequestParam(required = false) String position) {
        return employeeService.getEmployee(
                Optional.ofNullable(position)
                        .filter(p -> !p.isEmpty())
                        .orElse(null));
    }
    @GetMapping("/{id}/fullInfo")
    public EmployeeDTO getEmployeeFullInfo(@PathVariable int id) {
        return employeeService.getEmployeeFullInfo(id);
    }
    @GetMapping("/page")
    public List<EmployeeDTO> getEmployeesFromPage(@RequestParam(required = false, defaultValue = "0")  int page) {
        return employeeService.getEmployeesFromPage(page);

    }
}



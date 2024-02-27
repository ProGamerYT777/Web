package ru.skypro.lessons.springboot.web.controller;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    public Integer showHighAverageSalaries() {
        return employeeService.highAverageSalariesEmployees();
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/")
    public void createEmployee(@RequestBody Employee employee) {
        employeeService.createEmployee(employee);
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public void updateEmployeeById(@PathVariable("id") @RequestBody Employee employee) {
        employeeService.updateEmployeeById(employee);
    }
    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable("id") Integer id) {
        return employeeService.getEmployeeById(id);
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        employeeService.deleteById(id);
    }
    @GetMapping("/salaryHigherThan?salary=")
    public List<Employee> showEmployeesSalaryHighThan(@RequestParam("salary") Integer salary) {
        return employeeService.employeesSalaryHighThan(salary);
    }
    @GetMapping("/withHighestSalary")
        public List<Employee> showEmployeeWithHighestSalary() {
            return employeeService.getEmployeeWithHighestSalary();
    }
    @GetMapping
        public List<Employee> getEmployeesByPositionLike(@RequestParam("position") String position) {
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
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public void upload(@RequestParam("file") MultipartFile file) throws IOException {
        employeeService.upload(file);
    }
}



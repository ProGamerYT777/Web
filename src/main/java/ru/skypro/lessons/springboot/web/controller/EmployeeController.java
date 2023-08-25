package ru.skypro.lessons.springboot.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.classgraph.Resource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.web.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.web.model.Employee;
import ru.skypro.lessons.springboot.web.model.EmployeeFullInfo;
import ru.skypro.lessons.springboot.web.model.Position;
import ru.skypro.lessons.springboot.web.model.Report;
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
    public Optional<Employee> getEmployeeById(@PathVariable("id") Integer id) {
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
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public void upload(@RequestParam("file") MultipartFile file) throws IOException {
        employeeService.upload(file);
    }
    @PostMapping(value = "/report")
        public String reportToFile(Report report) {
        return employeeService.reportToFile(report);
    }
    @GetMapping(value = "/report/{id}")
        public ResponseEntity<Resource> downloadFile(@PathVariable("id") Integer id) {
        return employeeService.downloadFile(id);
    }
}


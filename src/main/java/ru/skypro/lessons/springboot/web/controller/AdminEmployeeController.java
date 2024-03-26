package ru.skypro.lessons.springboot.web.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.web.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.web.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("admin/employee")
public class AdminEmployeeController {

    private final EmployeeService employeeService;
    public AdminEmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping
    public List<EmployeeDTO> addNewEmployee(@RequestBody List<EmployeeDTO> employeeDTOS) {
        return employeeService.addEmployee(employeeDTOS);
    }

    @PutMapping("/{id}")
    public void editEmployee(@PathVariable int id, @RequestBody EmployeeDTO employeeDTO) {
        employeeService.update(id, employeeDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
    }

}

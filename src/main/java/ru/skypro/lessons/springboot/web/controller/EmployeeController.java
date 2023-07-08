package ru.skypro.lessons.springboot.web.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.web.model.Employee;
import ru.skypro.lessons.springboot.web.model.EmployeeFullInfo;
import ru.skypro.lessons.springboot.web.model.Position;
import ru.skypro.lessons.springboot.web.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
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


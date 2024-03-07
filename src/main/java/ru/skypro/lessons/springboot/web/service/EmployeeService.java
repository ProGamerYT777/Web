package ru.skypro.lessons.springboot.web.service;

import io.github.classgraph.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.web.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.web.model.Employee;
import ru.skypro.lessons.springboot.web.model.EmployeeFullInfo;
import ru.skypro.lessons.springboot.web.model.Position;
import ru.skypro.lessons.springboot.web.model.Report;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<EmployeeDTO> getAllNew();

    double salarySum();

    EmployeeDTO minSalary();

    EmployeeDTO maxSalary();

    Integer employeeHighSalary();

    List<EmployeeDTO> addEmployee(List<EmployeeDTO> employeeDTOS);
    void update(int id, EmployeeDTO employeeDTO);

    EmployeeDTO getEmployeeById(int id);

    void deleteEmployee(int id);

    List<EmployeeDTO> salaryHigherThan(Integer than);
    List<EmployeeDTO> withHighestSalary();
    List<EmployeeDTO> getEmployee(String e);
    EmployeeDTO getEmployeeFullInfo(int id);
    List<EmployeeDTO> getEmployeesFromPage(int page);

}

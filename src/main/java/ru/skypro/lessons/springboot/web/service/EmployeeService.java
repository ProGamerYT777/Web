package ru.skypro.lessons.springboot.web.service;

import ru.skypro.lessons.springboot.web.dto.EmployeeDTO;

import java.util.List;

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

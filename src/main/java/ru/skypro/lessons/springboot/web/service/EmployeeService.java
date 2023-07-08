package ru.skypro.lessons.springboot.web.service;

import org.springframework.data.repository.CrudRepository;
import ru.skypro.lessons.springboot.web.model.Employee;
import ru.skypro.lessons.springboot.web.model.EmployeeFullInfo;
import ru.skypro.lessons.springboot.web.model.Position;

import java.util.List;

public interface EmployeeService {

    List<Employee> getEmployeeWithHighestSalary();
    List<Employee> getEmployeesByPositionLike(Position position);
    List<EmployeeFullInfo> getFullInfo(Integer id);
    List<Employee> getPageInfo(int pageIndex, int unitPerPage);

}

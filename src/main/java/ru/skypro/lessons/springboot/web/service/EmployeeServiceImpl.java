package ru.skypro.lessons.springboot.web.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.web.model.Employee;
import ru.skypro.lessons.springboot.web.model.EmployeeFullInfo;
import ru.skypro.lessons.springboot.web.model.Position;
import ru.skypro.lessons.springboot.web.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getEmployeeWithHighestSalary() {
        return employeeRepository.getEmployeeWithHighestSalary();
    }

    @Override
    public List<Employee> getEmployeesByPositionLike(Position position) {
        return employeeRepository.getEmployeesByPositionLike(position);
    }

    @Override
    public List<EmployeeFullInfo> getFullInfo(Integer id) {
        return employeeRepository.getFullInfo(id);
    }

    @Override
    public List<Employee> getPageInfo(int pageIndex, int unitPerPage) {
        Pageable employeeOfConcretePage = PageRequest.of(pageIndex, unitPerPage);
        Page<Employee> page = employeeRepository.findAll(employeeOfConcretePage);
        return page.stream()
                .toList();
    }
}


package service;

import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.web.Employee;
import ru.skypro.lessons.springboot.web.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> sumSalariesEmployees() {
        return employeeRepository.sumSalariesEmployees();
    }

    @Override
    public List<Employee> minSalaryEmployee() {
        return employeeRepository.minSalaryEmployee();
    }

    @Override
    public List<Employee> maxSalaryEmployee() {
        return employeeRepository.maxSalaryEmployee();
    }

    @Override
    public List<Employee> highAverageSalariesEmployees() {
        return employeeRepository.highAverageSalariesEmployees();
    }
}


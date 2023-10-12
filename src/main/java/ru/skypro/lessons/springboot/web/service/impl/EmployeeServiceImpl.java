package ru.skypro.lessons.springboot.web.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.web.model.Employee;
import ru.skypro.lessons.springboot.web.model.EmployeeFullInfo;
import ru.skypro.lessons.springboot.web.model.Position;
import ru.skypro.lessons.springboot.web.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.web.service.EmployeeService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Override
    public double sumSalariesEmployees() {
        logger.info("Was invoked method to get the amount of employees salaries");
        return employeeRepository.sumSalariesEmployees();
    }

    @Override
    public Employee minSalaryEmployee() {
        logger.info("Was invoked method to get an employee with a minimum salary");
        return employeeRepository.minSalaryEmployee();
    }

    @Override
    public Employee maxSalaryEmployee() {
        logger.info("Was invoked method to get an employee with the maximum salary");
        return employeeRepository.maxSalaryEmployee();
    }

    @Override
    public List<Employee> highAverageSalariesEmployees() {
        logger.info("Was invoked method to get all employees whose salary is higher than the average");
        return employeeRepository.highAverageSalariesEmployees();
    }

    @Override
    public void createEmployee(Employee employee) {
        logger.info("Was invoked method for create employee " + employee);
        employeeRepository.save(employee);
    }

    @Override
    public void updateEmployeeById(Employee employee) {
        logger.info("Was invoked method to edit the employee by id " + employee);
        employeeRepository.updateEmployeeById(employee);
    }

    @Override
    public Optional<Employee> getEmployeeById(Integer id) {
        logger.info("Was invoked method to get an employee by id " + id);
        return employeeRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        logger.info("Was invoked method to delete an employee by id " + id);
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> employeesSalaryHighThan(int salary) {
        logger.info("Was invoked method to get all employees whose salary is higher than the salary parameter " + salary);
        return employeeRepository.employeesSalaryHighThan(salary);
    }

    @Override
    public List<Employee> getEmployeeWithHighestSalary() {
        logger.info("Was invoked method to get information about employees with the highest salary in the company");
        return employeeRepository.getEmployeeWithHighestSalary();
    }

    @Override
    public List<Employee> getEmployeesByPositionLike(Position position) {
        logger.info("Was invoked method to get information about all employees of the company specified in the position parameter " + position);
        return employeeRepository.getEmployeesByPositionLike(position);
    }

    @Override
    public List<EmployeeFullInfo> getFullInfo(Integer id) {
        logger.info("Was invoked method to get complete information about the employee with the passed ID" + id);
        return employeeRepository.getFullInfo();
    }

    @Override
    public List<Employee> getPageInfo(int pageIndex, int unitPerPage) {
        logger.info("Was invoked method to get information about employees based on the page number " + pageIndex, unitPerPage);
        Pageable employeeOfConcretePage = PageRequest.of(pageIndex, unitPerPage);
        Page<Employee> page = employeeRepository.findAll(employeeOfConcretePage);
        return page.stream()
                .toList();
    }

    @Override
    public void upload(MultipartFile file) throws IOException {
        logger.info("Was invoked method to process the file and create employee objects " + file);
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = file.getInputStream();
        List<Employee> employees = objectMapper.readValue(inputStream, new TypeReference<List<Employee>>() {
        });
        employeeRepository.saveAll(employees);
        }
}

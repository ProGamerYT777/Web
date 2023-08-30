package ru.skypro.lessons.springboot.web.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.classgraph.Resource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.web.model.Employee;
import ru.skypro.lessons.springboot.web.model.EmployeeFullInfo;
import ru.skypro.lessons.springboot.web.model.Position;
import ru.skypro.lessons.springboot.web.model.Report;
import ru.skypro.lessons.springboot.web.repository.EmployeeRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Override
    public double sumSalariesEmployees() {
        return employeeRepository.sumSalariesEmployees();
    }

    @Override
    public Employee minSalaryEmployee() {
        return employeeRepository.minSalaryEmployee();
    }

    @Override
    public Employee maxSalaryEmployee() {
        return employeeRepository.maxSalaryEmployee();
    }

    @Override
    public List<Employee> highAverageSalariesEmployees() {
        return employeeRepository.highAverageSalariesEmployees();
    }

    @Override
    public void createEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void updateEmployeeById(Employee employee) {
        employeeRepository.updateEmployeeById(employee);
    }

    @Override
    public Optional<Employee> getEmployeeById(Integer id) {
        return employeeRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> employeesSalaryHighThan(int salary) {
        return employeeRepository.employeesSalaryHighThan(salary);
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

    @Override
    public void upload(MultipartFile file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = file.getInputStream();
        List<Employee> employees = objectMapper.readValue(inputStream, new TypeReference<List<Employee>>() {
        });
        employeeRepository.saveAll(employees);
        }

    @Override
    public String reportToFile(Report report) {
        return employeeRepository.reportToFile(report);
    }

    @Override
    public ResponseEntity<Resource> downloadFile(Integer id, Report report) {
        ByteArrayResource resource = new ByteArrayResource(report.getFileReport(id));
        return employeeRepository.downloadFile(resource);
    }
}

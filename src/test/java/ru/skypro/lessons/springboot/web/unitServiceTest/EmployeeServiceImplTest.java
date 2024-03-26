package ru.skypro.lessons.springboot.web.unitServiceTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import ru.skypro.lessons.springboot.web.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.web.mapper.EmployeeMapper;
import ru.skypro.lessons.springboot.web.model.Employee;
import ru.skypro.lessons.springboot.web.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.web.service.impl.EmployeeServiceImpl;
import ru.skypro.lessons.springboot.web.service.impl.ReportServiceImpl;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository repositoryMock;
    @Spy
    private EmployeeMapper employeeMapper;
    @InjectMocks
    private EmployeeServiceImpl employeeService;
    @InjectMocks
    private ReportServiceImpl out;

    @Test
    void getAllNewTest() {
        final int id = 1;
        final String inputName = "Vasya";
        final int inputSalary = 20000;
        Employee employee = new Employee(inputName, inputSalary);
        employee.setId(id);
        final List<EmployeeDTO> employees = getIterable(id, inputName, inputSalary);
        final List<Employee> employees1 = List.of(employee);
        when(repositoryMock.findAll())
                .thenReturn(employees1);
        List<EmployeeDTO> actual = employeeService.getAllNew();

        assertEquals(employees.size(), actual.size());

    }

    @Test
    void getMinimumSalaryEmployeeInDepartment() {
        final int id = 1;
        final String inputName = "Vasya";
        final int inputSalary = 20000;
        EmployeeDTO employeeDTO = new EmployeeDTO(id, inputName, inputSalary, "java");
        when(repositoryMock.minSalary())
                .thenReturn(Optional.of(employeeDTO));

        assertEquals(employeeDTO, employeeService.minSalary());
    }

    @Test
    void getMaximumSalaryEmployeeInDepartment() {
        final int id = 1;
        final String inputName = "Vasya";
        final int inputSalary = 220000;
        List<EmployeeDTO> list = List.of(
                new EmployeeDTO(id, inputName, inputSalary, "java"), new EmployeeDTO(id, inputName, 300000, "java"));
        EmployeeDTO employeeDTO = list.stream()
                .max(Comparator.comparing(EmployeeDTO::getSalary)).get();
        when(repositoryMock.maxSalary())
                .thenReturn(list);

        EmployeeDTO actual = employeeService.maxSalary();
        assertEquals(employeeDTO, actual);
    }

    @Test
    void salarySumTest() {
        double sum = 22000;
        when(repositoryMock.salarySum())
                .thenReturn(sum);

        double actual = employeeService.salarySum();
        assertEquals(sum, actual);
    }

    @Test
    void employeeHighSalaryTest() {
        int avg = 10000;
        when(repositoryMock.employeeHighSalary())
                .thenReturn(avg);

        int actual = employeeService.employeeHighSalary();
        assertEquals(avg, actual);
    }

    @Test
    void addEmployeeTest() {
        final int id = 1;
        final String inputName = "Vasya";
        final int inputSalary = 20000;
        Employee employee = new Employee(inputName, inputSalary);
        employee.setId(id);
        List<EmployeeDTO> employeeDTOS = getIterable(id, inputName, inputSalary);
        final List<Employee> employees1 = List.of(employee);

        when(repositoryMock.saveAll(employees1))
                .thenReturn(employees1)
                .thenAnswer(i -> {
                    throw new RuntimeException(" ");
                });

        List<EmployeeDTO> actual = new ArrayList<>();
        actual.add(employeeService.addEmployee(employeeDTOS).get(0));

        assertEquals(employeeDTOS.size(), actual.size());
    }

    @Test
    void updateEmployeeTest() {
        final int id = 1;
        final String inputName = "Vasya";
        final int inputSalary = 20000;

        EmployeeDTO actual = new EmployeeDTO(id, "inputName", 1000, "java");
        Employee employee = new Employee(inputName, inputSalary);
        employee.setId(id);

        when(repositoryMock.findById(id))
                .thenReturn(Optional.of(employee));
        when(repositoryMock.save(employee))
                .thenReturn(employee);
        employeeService.update(id, actual);

        assertEquals(employee.getName(), actual.getName());
        assertEquals(employee.getSalary(), actual.getSalary());
    }

    @Test
    void getEmployeeTest() {
        int id = 1;
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName("John");
        employee.setSalary(2222);
        repositoryMock.save(employee);
        when(repositoryMock.findById(employee.getId()))
                .thenReturn(Optional.of(employee))
                .thenAnswer(i -> {
                    throw new RuntimeException(" ");
                });
        EmployeeDTO employee1 = employeeService.getEmployeeById(id);
        assertEquals(employee.getName(), employee1.getName());
        assertEquals(employee.getSalary(), employee1.getSalary());
        assertEquals(employee.getId(), employee1.getId());
    }

    @Test
    void testDeleteEmployee() {
        int id = 1;
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName("John");
        employee.setSalary(2222);

        when(repositoryMock.findById(1))
                .thenReturn(Optional.of(employee));

        employeeService.deleteEmployee(id);

        verify(repositoryMock, times(1)).delete(employee);


    }

    @Test
    void salaryHigherThanTest() {
        int than = 1000;
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("John");
        employee.setSalary(2222);
        List<Employee> employees = List.of(employee);

        when(repositoryMock.findEmployeeBySalaryIsGreaterThan(than))
                .thenReturn(employees);
        List<EmployeeDTO> employeeDTOS = employeeService.salaryHigherThan(than);
        assertEquals(employees.size(), employeeDTOS.size());
    }

    @Test
    void withHighestSalaryTest() {
        final int id = 1;
        final String inputName = "Vasya";
        final int inputSalary = 20000;
        List<EmployeeDTO> employees = getIterable(id, inputName, inputSalary);
        when(repositoryMock.maxSalary())
                .thenReturn(employees);
        EmployeeDTO actual = employeeService.maxSalary();

        assertEquals(employees.get(0), actual);
    }

    @Test
    void getEmployeeFullInfoTest() {
        final int id = 1;
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName("John");
        employee.setSalary(2222);
        when(repositoryMock.findById(id))
                .thenReturn(Optional.of(employee));

        EmployeeDTO actual = employeeService.getEmployeeFullInfo(id);
        assertEquals(employee.getName(), actual.getName());
        assertEquals(employee.getSalary(), actual.getSalary());
        assertEquals(employee.getId(), actual.getId());
    }

    @Test
    void getEmployeesFromPage() {
        int page = 3;

        final int id = 1;
        final String inputName = "Vasya";
        final int inputSalary = 20000;
        Employee employee = new Employee(inputName, inputSalary);
        employee.setId(id);

        final List<Employee> employees1 = List.of(employee, employee, employee, employee);
        when(repositoryMock.findAll())
                .thenReturn(employees1);

        List<Employee> actual = repositoryMock.findAll().stream().limit(page).toList();
        assertEquals(actual.size(), page);

    }    @Test
    public void ShouldAddEmployeeFromFileToDB() throws IOException {
        List<EmployeeDTO> employeeDTOExpected = List.of(new EmployeeDTO(3, "Irina", 150000, "Analyst"));

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(employeeDTOExpected);
        MockMultipartFile file = new MockMultipartFile("employee", "employee.json", MediaType.MULTIPART_FORM_DATA_VALUE, json.getBytes());
        List<EmployeeDTO> actual = objectMapper.readValue(json, new TypeReference<>() {
        });
        assertEquals(employeeDTOExpected, actual);


    }



    private static List<EmployeeDTO> getIterable(int id,
                                                 String inputName,
                                                 int inputSalary) {

        return List.of(new EmployeeDTO(id, inputName, inputSalary, "java")
        );
    }
    private static String readTextFromFile(String fileName) {
        try {

            return Files.lines(Paths.get(fileName), Charset.forName("windows-1251"))
                    .collect(Collectors.joining());
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return "error";
        }
    }
}

package ru.skypro.lessons.springboot.web.unitServiceTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import ru.skypro.lessons.springboot.web.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.web.model.Employee;
import ru.skypro.lessons.springboot.web.model.Position;
import ru.skypro.lessons.springboot.web.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.web.service.impl.EmployeeServiceImpl;

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
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void getMinimumSalaryEmployee() {
        final int id = 1;
        final String inputName = "Kirill";
        final int inputSalary = 20000;
        Employee employee = new Employee(id, inputName, inputSalary, new Position(0, "Java"));
        when(repositoryMock.minSalaryEmployee())
                .thenReturn(employee);

        assertEquals(employee, employeeService.minSalaryEmployee());
    }

    @Test
    void getMaximumSalaryEmployee() {
        final int id = 1;
        final String inputName = "Kirill";
        final int inputSalary = 220000;
        List<Employee> list = List.of(
                new Employee(id, inputName, inputSalary, new Position(0, "Java")),
                new Employee(id, inputName, 300000, new Position(1, "Java")));
        Employee employee = list.stream()
                .max(Comparator.comparing(Employee::getSalary)).get();
        when(repositoryMock.maxSalaryEmployee())
                .thenReturn((Employee) list);

        Employee actual = employeeService.maxSalaryEmployee();
        assertEquals(employee, actual);
    }

    @Test
    void salarySumTest() {
        double sum = 22000;
        when(repositoryMock.sumSalariesEmployees())
                .thenReturn(sum);

        double actual = employeeService.sumSalariesEmployees();
        assertEquals(sum, actual);
    }

    @Test
    void employeeHighAverageSalaryTest() {
        int avg = 10000;
        when(repositoryMock.highAverageSalariesEmployees())
                .thenReturn(avg);

        int actual = employeeService.highAverageSalariesEmployees();
        assertEquals(avg, actual);
    }

//    @Test
//    void createEmployeeTest() {
//        final int id = 1;
//        final String inputName = "Kirill";
//        final int inputSalary = 20000;
//        Employee employee = new Employee(inputName, inputSalary);
//        employee.setId(id);
//        List<Employee> employee1 = getIterable(id, inputName, inputSalary);
//        final List<Employee> employees1 = List.of(employee);
//
//        when(repositoryMock.saveAll(employees1))
//                .thenReturn(employees1)
//                .thenAnswer(i -> {
//                    throw new RuntimeException(" ");
//                });
//
//        List<Employee> actual = new ArrayList<>();
//        actual.add(employeeService.createEmployee(employee1));
//
//        assertEquals(employee1.size(), actual.size());
//    }

    @Test
    void updateEmployeeTest() {
        final int id = 1;
        final String inputName = "Kirill";
        final int inputSalary = 20000;

        Employee actual = new Employee(id, "inputName", 1000, new Position(0, "Java"));
        Employee employee = new Employee(inputName, inputSalary);
        employee.setId(id);

        when(repositoryMock.findById(id))
                .thenReturn(Optional.of(employee));
        when(repositoryMock.save(employee))
                .thenReturn(employee);
        employeeService.updateEmployeeById(actual);

        assertEquals(employee.getName(), actual.getName());
        assertEquals(employee.getSalary(), actual.getSalary());
    }

    @Test
    void getEmployeeTest() {
        int id = 1;
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName("Anton");
        employee.setSalary(2222);
        repositoryMock.save(employee);
        when(repositoryMock.findById(employee.getId()))
                .thenReturn(Optional.of(employee))
                .thenAnswer(i -> {
                    throw new RuntimeException(" ");
                });
        Optional<Employee> employee1 = employeeService.getEmployeeById(id);
        assertEquals(employee.getName(), employee1.get().getName());
        assertEquals(employee.getSalary(), employee1.get().getSalary());
        assertEquals(employee.getId(), employee1.get().getId());
    }

    @Test
    void testDeleteEmployee() {
        int id = 1;
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName("Anton");
        employee.setSalary(2222);

        when(repositoryMock.findById(1))
                .thenReturn(Optional.of(employee));

        employeeService.deleteById(id);

        verify(repositoryMock, times(1)).delete(employee);


    }

    @Test
    void salaryHigherThanTest() {
        int than = 1000;
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Anton");
        employee.setSalary(2222);
        List<Employee> employees = List.of(employee);

        when(repositoryMock.employeesSalaryHighThan(than))
                .thenReturn(employees);
        List<Employee> employeeDTO = employeeService.employeesSalaryHighThan(than);
        assertEquals(employees.size(), employeeDTO.size());
    }

    @Test
    void withHighestSalaryTest() {
        final int id = 1;
        final String inputName = "Kirill";
        final int inputSalary = 20000;
        List<Employee> employees = getIterable(id, inputName, inputSalary);
        when(repositoryMock.maxSalaryEmployee())
                .thenReturn((Employee) employees);
        EmployeeDTO actual = EmployeeDTO.fromEmployee(employeeService.maxSalaryEmployee());

        assertEquals(employees.get(0), actual);
    }

    @Test
    void getEmployeeFullInfoTest() {
        final int id = 1;
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName("Anton");
        employee.setSalary(2222);
        when(repositoryMock.findById(id))
                .thenReturn(Optional.of(employee));

        EmployeeDTO actual = (EmployeeDTO) employeeService.getFullInfo(id);
        assertEquals(employee.getName(), actual.getName());
        assertEquals(employee.getSalary(), actual.getSalary());
        assertEquals(employee.getId(), actual.getId());
    }

    @Test
    void getEmployeesFromPage() {
        int page = 3;

        final int id = 1;
        final String inputName = "Kirill";
        final int inputSalary = 20000;
        Employee employee = new Employee(inputName, inputSalary);
        employee.setId(id);

        final List<Employee> employees1 = List.of(employee, employee, employee, employee);
        when(repositoryMock.findAll())
                .thenReturn(employees1);

        List<Employee> actual = repositoryMock.findAll().stream().limit(page).toList();
        assertEquals(actual.size(), page);
    }
    @Test
    public void ShouldAddEmployeeFromFileToDB() throws IOException {
        Employee employee = new Employee(1, "Anastasia", 150000, new Position(0, "Analyst"));

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(employee);
        MockMultipartFile file = new MockMultipartFile("employee", "employee.json", MediaType.MULTIPART_FORM_DATA_VALUE, json.getBytes());
        List<EmployeeDTO> actual = objectMapper.readValue(json, new TypeReference<>() {
        });
        assertEquals(employee, actual);


    }


    private static List<Employee> getIterable(int id, String inputName, int inputSalary) {
        return List.of(new Employee(id, inputName, inputSalary, new Position(0, "Java")));
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

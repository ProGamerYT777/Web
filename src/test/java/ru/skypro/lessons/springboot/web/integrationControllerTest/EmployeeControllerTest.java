package ru.skypro.lessons.springboot.web.integrationControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.skypro.lessons.springboot.web.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.web.model.Employee;
import ru.skypro.lessons.springboot.web.model.EmployeeFullInfo;
import ru.skypro.lessons.springboot.web.model.Position;
import ru.skypro.lessons.springboot.web.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.web.repository.ReportRepository;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    public EmployeeRepository employeeRepository;
    @Autowired
    public ReportRepository reportRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    private void cleanData() {
        employeeRepository.deleteAll();
    }

    private Employee createTestEmployee(String name) {
        Employee employee = new Employee(name);
        return employeeRepository.save(employee);
    }

    @Test
    void getEmployeeInDatabase_thenEmptyJsonArray() throws Exception {
        mockMvc.perform(get("/employee")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$").isArray()).
                andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void deleteEmployeeById_thenCheckNotContainEmployee() throws Exception {
        Employee employee = new Employee(1, "Andrey", 1200, new Position(0, "developer"));
        mockMvc.perform(delete("/employee/{id}", employee.getId()))
                .andExpect(status().isOk());

    }

    @Test
    void addEmployee_test() throws Exception {
        List<Employee> employee = new ArrayList<>();
        employee.add(new Employee(1, "Andrey", 12000, new Position(0, "test")));
        mockMvc.perform(post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Andrey"));

    }

    @Test
    void editEmployee_changName() throws Exception {
        int id = createTestEmployee("Nikolay").getId();
        mockMvc.perform(put("/employee/{id}", id)
                        .content(objectMapper.writeValueAsString(new Employee(id, "Michail")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(get("/employee")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("Michail"));
    }


    @Test
    void getShowSalary_test() throws Exception {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Ivan", 1000));
        employees.add(new Employee("Ilya", 10000));
        employees.add(new Employee("Dmitry", 1000));
        employees.add(new Employee("Petr", 2000));

        mockMvc.perform(post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employees)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/employee/salary/sum")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$").value(14000));
    }

    @Test
    void getShowAvgSalary_test() throws Exception {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Ivan", 1000));
        employees.add(new Employee("Ilya", 10000));
        employees.add(new Employee("Dmitry", 1000));
        employees.add(new Employee("Petr", 2000));

        mockMvc.perform(post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employees)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/employee/salary/avg")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$").value(3500));
    }

    @Test
    void getShowSalaryMin_test() throws Exception {
        List<EmployeeDTO> employees = new ArrayList<>();
        employees.add(new EmployeeDTO("Ivan", 1001, new Position(0, "test")));
        employees.add(new EmployeeDTO("Ilya", 10000, new Position(0, "test")));
        employees.add(new EmployeeDTO("Dmitry", 1000, new Position(0, "test")));
        employees.add(new EmployeeDTO("Petr", 2000, new Position(0, "test")));

        mockMvc.perform(post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employees)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/employee/salary/min")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.[0].name").value("Dmitry"));
    }

    @Test
    void getShowSalaryMax_test() throws Exception {
        List<EmployeeDTO> employees = new ArrayList<>();
        employees.add(new EmployeeDTO("Ivan", 1001, new Position(0, "test")));
        employees.add(new EmployeeDTO("Ilya", 10000, new Position(0, "test")));
        employees.add(new EmployeeDTO("Dmitry", 1000, new Position(0, "test")));
        employees.add(new EmployeeDTO("Petr", 2000, new Position(0, "test")));

        mockMvc.perform(post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employees)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/employee/salary/max")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.[0].name").value("Ilya"));
    }

    @Test
    void getEmployeesWithSalaryHigherThan_test() throws Exception {
        List<EmployeeDTO> employees = new ArrayList<>();
        employees.add(new EmployeeDTO("Ivan", 1001, new Position(0, "test")));
        employees.add(new EmployeeDTO("Ilya", 10000, new Position(0, "test")));
        employees.add(new EmployeeDTO("Dmitry", 1000, new Position(0, "test")));
        employees.add(new EmployeeDTO("Petr", 2000, new Position(0, "test")));

        mockMvc.perform(post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employees)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/employee/salaryHigherThan")
                        .param("salary", String.valueOf(9000))).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.[0].name").value("Ilya"));
    }

    @Test
    void getEmployeesByIdWithRequired_Test() throws Exception {
        List<EmployeeDTO> employees = new ArrayList<>();
        employees.add(new EmployeeDTO("Ivan", 1001, new Position(0, "test")));
        employees.add(new EmployeeDTO("Ilya", 10000, new Position(0, "test")));
        employees.add(new EmployeeDTO("Dmitry", 1000, new Position(0, "test")));
        employees.add(new EmployeeDTO("Petr", 2000, new Position(0, "test")));
        int id = 1;

        mockMvc.perform(post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employees)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/employee/{id}", id)).
                andExpect(status().isOk());

    }

    @Test
    void getEmployeesFull_Test() throws Exception {
        List<EmployeeFullInfo> employeeFullInfo = new ArrayList<>();
        employeeFullInfo.add(new EmployeeFullInfo("Roman", 12000, null));
        employeeFullInfo.add(new EmployeeFullInfo("Vasily", 11000, null));
        employeeFullInfo.add(new EmployeeFullInfo("Vladislav", 1000, null));
        int id = 1;

        mockMvc.perform(get("/employee/fullInfo/{id}", id)).
                andExpect(status().isOk()).
                andExpect(jsonPath("$").isArray()).
                andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void getEmployeesWithPaging_Test() throws Exception {
        List<EmployeeFullInfo> employeeFullInfo = new ArrayList<>();
        employeeFullInfo.add(new EmployeeFullInfo("Roman", 12000, null));
        employeeFullInfo.add(new EmployeeFullInfo("Vasily", 11000, null));
        employeeFullInfo.add(new EmployeeFullInfo("Vladislav", 1000, null));

        mockMvc.perform(get("/employee/paging")
                        .param("page", String.valueOf(1))
                        .param("size", String.valueOf(1))).
                andExpect(status().isOk());
    }

    @Test
    void withHighestSalary_Test() throws Exception {
        List<EmployeeFullInfo> employeeFullInfo = new ArrayList<>();
        employeeFullInfo.add(new EmployeeFullInfo("Roman", 12000, null));
        employeeFullInfo.add(new EmployeeFullInfo("Vasily", 11000, null));
        employeeFullInfo.add(new EmployeeFullInfo("Vladislav", 1000, null));

        mockMvc.perform(get("/employee/withHighestSalary")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$").isArray()).
                andExpect(jsonPath("$").isEmpty());

    }

    @Test
    void withLowSalary_Test() throws Exception {
        List<EmployeeFullInfo> employeeFullInfo = new ArrayList<>();
        employeeFullInfo.add(new EmployeeFullInfo("Roman", 12000, null));
        employeeFullInfo.add(new EmployeeFullInfo("Vasily", 11000, null));
        employeeFullInfo.add(new EmployeeFullInfo("Vladislav", 1000, null));

        mockMvc.perform(get("/employee/withLowSalary")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$").isArray()).
                andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void getEmployeesFullPosition_Test() throws Exception {
        List<EmployeeFullInfo> employeeFullInfo = new ArrayList<>();
        employeeFullInfo.add(new EmployeeFullInfo("Roman", 12000, "developer"));
        employeeFullInfo.add(new EmployeeFullInfo("Vasily", 11000, null));
        employeeFullInfo.add(new EmployeeFullInfo("Vladislav", 1000, null));

        mockMvc.perform(get("/employee/position")
                        .param("position", "developer")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$").isArray()).
                andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void uploadFile_Test() throws Exception {

        MockMultipartFile file
                = new MockMultipartFile(
                "file",("[{\"id\": 1," +
                " \"name\": \"Andrey\"," +
                "\"salary\": 10000," +
                "\"position\": {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"Tester\"}}]")
                .getBytes()
        );
        mockMvc.perform(MockMvcRequestBuilders
                        .multipart(HttpMethod.POST, "/employee/upload")
                        .file(file))
                .andExpect(status().isOk());
    }

}

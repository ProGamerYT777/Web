package ru.skypro.lessons.springboot.web.integrationControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.skypro.lessons.springboot.web.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.web.dto.PositionDTO;
import ru.skypro.lessons.springboot.web.model.Employee;
import ru.skypro.lessons.springboot.web.model.EmployeeFullInfo;
import ru.skypro.lessons.springboot.web.model.Position;
import ru.skypro.lessons.springboot.web.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.web.repository.ReportRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = { "spring.liquibase.enabled=false" })
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    private void cleanData() {
        employeeRepository.deleteAll();

    }

    @Test
    @SneakyThrows
    void getEmployeesTest() throws Exception {
        int expectedCount = 5;
        employeeRepository.deleteAll();
        mockMvc.perform(get("/employee"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
        employeeRepository.saveAll(employees(expectedCount));
        mockMvc.perform(get("/employee"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(expectedCount));


    }

    @Test
    void salarySumTest() throws Exception {

        JSONObject employee1 = new JSONObject();
        employee1.put("id", 1);
        employee1.put("name", "Kate");
        employee1.put("salary", 100000);

        JSONObject employee3 = new JSONObject();
        employee3.put("id", 3);
        employee3.put("name", "Ivan");
        employee3.put("salary", 30000);

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(employee1);
        jsonArray.put(employee3);

        System.out.println(jsonArray);
        mockMvc.perform(post("/admin/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonArray.toString()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        mockMvc.perform(get("/employee/salary/sum"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(130000))
        ;

    }

    @Test
    void givenThereIsListOfEmployeeCreated_whenGetAllEmployeesMaxSalary_thenTheyExistInList() throws Exception {


        JSONObject employee1 = new JSONObject();
        employee1.put("id", 1);
        employee1.put("name", "Kate");
        employee1.put("salary", 100000);


        JSONObject employee2 = new JSONObject();
        employee2.put("id", 2);
        employee2.put("name", "Alex");
        employee2.put("salary", 120000);


        JSONObject employee3 = new JSONObject();
        employee3.put("id", 3);
        employee3.put("name", "Ivan");
        employee3.put("salary", 30000);

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(employee3);
        jsonArray.put(employee2);
        jsonArray.put(employee1);

        System.out.println(jsonArray);
        mockMvc.perform(post("/admin/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonArray.toString()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();


        mockMvc.perform(get("/employee/salary/max"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salary").value(120000));
    }

    @Test
    void givenThereIsListOfEmployeeCreated_whenGetAllEmployeesMinSalary_thenTheyExistInList() throws Exception {


        JSONObject employee1 = new JSONObject();
        employee1.put("id", 1);
        employee1.put("name", "Kate");
        employee1.put("salary", 100000);


        JSONObject employee2 = new JSONObject();
        employee2.put("id", 2);
        employee2.put("name", "Alex");
        employee2.put("salary", 120000);


        JSONObject employee3 = new JSONObject();
        employee3.put("id", 3);
        employee3.put("name", "Ivan");
        employee3.put("salary", 300000);

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(employee3);
        jsonArray.put(employee2);
        jsonArray.put(employee1);

        System.out.println(jsonArray);
        mockMvc.perform(post("/admin/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonArray.toString()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();


        mockMvc.perform(get("/employee/salary/min"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salary").value(100000));
    }

    @Test
    void employeeForId() throws Exception {

        JSONObject employee1 = new JSONObject();
        employee1.put("id", 1);
        employee1.put("name", "Kate");
        employee1.put("salary", 100000);

        JSONObject employee3 = new JSONObject();
        employee3.put("id", 2);
        employee3.put("name", "Ivan");
        employee3.put("salary", 30000);

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(employee1);
        jsonArray.put(employee3);

        int id = employee1.getInt("id");

        mockMvc.perform(post("/admin/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonArray.toString()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        mockMvc.perform(get("/employee/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Kate"))
                .andExpect(jsonPath("$.salary").value(100000));

    }

    @Test
    void getAllEmployee() throws Exception {
        employeeRepository.deleteAll();
        JSONObject employee1 = new JSONObject();
        employee1.put("id", 1);
        employee1.put("name", "Kate");
        employee1.put("salary", 100000);

        JSONObject employee3 = new JSONObject();
        employee3.put("id", 2);
        employee3.put("name", "Ivan");
        employee3.put("salary", 30000);

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(employee1);
        jsonArray.put(employee3);

        mockMvc.perform(post("/admin/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonArray.toString()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        mockMvc.perform(get("/employee/all-employee-new"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));

    }

    @Test
    void getEmployeeFullInfo() throws Exception {
        employeeRepository.deleteAll();
        JSONObject position = new JSONObject();
        position.put("id", 1);
        position.put("name", "Java");

        JSONObject employee1 = new JSONObject();
        employee1.put("id", 6);
        employee1.put("name", "Ivan");
        employee1.put("salary", 100000);
        employee1.put("position_id", position.getInt("id"));

        JSONObject employee2 = new JSONObject();
        employee2.put("id", 7);
        employee2.put("name", "Polina");
        employee2.put("salary", 120000);
        employee2.put("position_id", position.getInt("id"));

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(employee1);
        jsonArray.put(employee2);

        mockMvc.perform(post("/admin/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonArray.toString()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        int id = employee2.getInt("id");
        mockMvc.perform(get("/employee/{id}/fullInfo", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(7))
                .andExpect(jsonPath("$.name").value("Polina"))
                .andExpect(jsonPath("$.salary").value(120000));
    }

    @Test
    void getEmployeeFromPage() throws Exception {
        JSONObject position = new JSONObject();
        position.put("id", 1);
        position.put("name", "Java");

        JSONObject employee1 = new JSONObject();
        employee1.put("id", 1);
        employee1.put("name", "Ivan");
        employee1.put("salary", 100000);
        employee1.put("position_id", position.getInt("id"));

        JSONObject employee2 = new JSONObject();
        employee2.put("id", 2);
        employee2.put("name", "Polina");
        employee2.put("salary", 120000);
        employee2.put("position_id", position.getInt("id"));

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(employee1);
        jsonArray.put(employee2);

        mockMvc.perform(post("/admin/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonArray.toString()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        int page = 1;
        mockMvc.perform(get("/employee/page", page))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));

    }

    static List<Employee> employees(int expectedCount) {
        return Stream.generate(() ->
                        new Employee("Petr", 12000))
                .limit(expectedCount)
                .toList();

    }
}

package ru.skypro.lessons.springboot.web.integrationControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import ru.skypro.lessons.springboot.web.model.Employee;
import ru.skypro.lessons.springboot.web.repository.EmployeeRepository;

import java.util.List;
import java.util.stream.Stream;

@SpringBootTest(properties = { "spring.liquibase.enabled=false" })
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AdminEmployeeController {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    private void cleanData() {
        employeeRepository.deleteAll();
    }

    @Test
    void givenNoUsersInDatabase_whenUserAdded_thenItExistsInList() throws Exception {
        JSONObject position = new JSONObject();
        position.put("id", 1);
        position.put("name", "Java");

        JSONObject employee = new JSONObject();
        employee.put("salary", 100000);
        employee.put("position_id", position.get("id"));
        employee.put("name", "Ivan");


        mockMvc.perform(post("/admin/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new JSONArray().put(employee).toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        mockMvc.perform(get("/employee"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Ivan"))
                .andExpect(jsonPath("$[0].salary").value(100000));
    }

    @Test
    void givenNoUsersInDatabase_whenUserAddedAndUpdate_thenItExistsInList() throws Exception {
        JSONObject position = new JSONObject();
        position.put("id", 1);
        position.put("name", "Java");

        JSONObject employee = new JSONObject();
        employee.put("id", 14);
        employee.put("salary", 100000);
        employee.put("position_id", position.get("id"));
        employee.put("name", "Ivan");

        mockMvc.perform(post("/admin/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new JSONArray().put(employee).toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andReturn().getResponse().getContentAsString();

        JSONObject createdEmployee = new JSONObject(String.valueOf(employee));

        int id = employee.getInt("id");
        createdEmployee.put("salary", 90000);
        createdEmployee.put("position_id", position.get("id"));
        createdEmployee.put("name", "Polina");

        mockMvc.perform(put("/admin/employee/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createdEmployee.toString()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/employee/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Polina"))
                .andExpect(jsonPath("$.salary").value(90000));
    }

    @Test
    void givenNoUsersInDatabase_whenUserAddedAndDelete_thenItExistsInList() throws Exception {
        JSONObject position = new JSONObject();
        position.put("id", 1);
        position.put("name", "Java");

        JSONObject employee = new JSONObject();
        employee.put("id", 13);
        employee.put("salary", 100000);
        employee.put("position_id", position.get("id"));
        employee.put("name", "Ivan");

        mockMvc.perform(post("/admin/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new JSONArray().put(employee).toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andReturn().getResponse().getContentAsString();
        int id = employee.getInt("id");
        mockMvc.perform(get("/employee/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Ivan"))
                .andExpect(jsonPath("$.salary").value(100000));
        mockMvc.perform(delete("/admin/employee/{id}", id))
                .andExpect(status().isOk());

        mockMvc.perform(get("/employee/{id}", id))
                .andExpect(status().isForbidden());
    }


    static List<Employee> employees(int expectedCount) {
        return Stream.generate(() ->
                        new Employee("Petr", 12000))
                .limit(expectedCount)
                .toList();

    }

}

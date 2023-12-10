package ru.skypro.lessons.springboot.web.integrationControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import ru.skypro.lessons.springboot.web.repository.EmployeeRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ReportControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    public EmployeeRepository employeeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    private void cleanData() {
        employeeRepository.deleteAll();
    }


    @Test
    void report_Test() throws Exception {
        mockMvc.perform(post("/report")).
                andExpect(status().isOk());
    }

    @Test
    void find_Test() throws Exception {

        mockMvc.perform(post("/report")).
                andExpect(status().isOk());
        int id = 1;

        mockMvc.perform(get("/report/{id}", id)).
                andExpect(status().isOk());

    }

    @Test
    void findFile_Test() throws Exception {

        mockMvc.perform(post("/report")).
                andExpect(status().isOk());
        int id = 1;

        mockMvc.perform(get("/report/file/{id}", id)).
                andExpect(status().isOk());

    }

}

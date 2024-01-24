package ru.skypro.lessons.springboot.web;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = { "spring.liquibase.enabled=false" })
public class WebApplicationTests {

    @Test
    void contextLoads() {
    }

}

package ru.skypro.lessons.springboot.web.service;

import io.github.classgraph.Resource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface ReportService {
    Integer reportToFile();
    ResponseEntity<Resource> downloadFile(Integer id) throws IOException;
}

package ru.skypro.lessons.springboot.web.service;

import io.github.classgraph.Resource;
import org.springframework.http.ResponseEntity;
import ru.skypro.lessons.springboot.web.model.Report;

import java.io.IOException;

public interface ReportService {
    Integer reportToFile(Report report);
    ResponseEntity<Resource> downloadFile(Integer id) throws IOException;
}

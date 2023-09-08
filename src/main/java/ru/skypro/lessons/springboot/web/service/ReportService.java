package ru.skypro.lessons.springboot.web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.classgraph.Resource;

import java.io.IOException;

public interface ReportService {
    Integer reportToFile() throws JsonProcessingException;
    Resource downloadFile(Integer id) throws IOException;
}

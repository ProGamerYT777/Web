package ru.skypro.lessons.springboot.web.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface ReportService {
    void reportToFile() throws JsonProcessingException;
    void downloadFile(Integer id) throws IOException;
}

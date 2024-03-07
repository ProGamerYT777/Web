package ru.skypro.lessons.springboot.web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.skypro.lessons.springboot.web.model.Report;

import java.io.File;
import java.io.IOException;

public interface ReportService {
    Integer createReport() throws IOException;

    void upload(File file) throws IOException, ClassNotFoundException;

    Report getReportById(int id);
}

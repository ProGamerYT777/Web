package ru.skypro.lessons.springboot.web.service;

import io.github.classgraph.Resource;

import java.io.IOException;

public interface ReportService {
    Integer reportToFile();
    Resource downloadFile(Integer id) throws IOException;
}

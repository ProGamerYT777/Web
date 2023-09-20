package ru.skypro.lessons.springboot.web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.web.model.EmployeeFullInfo;
import ru.skypro.lessons.springboot.web.model.Report;
import ru.skypro.lessons.springboot.web.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.web.repository.ReportRepository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final EmployeeRepository employeeRepository;

    public ReportServiceImpl(ReportRepository reportRepository, EmployeeRepository employeeRepository) {
        this.reportRepository = reportRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void reportToFile() throws JsonProcessingException {
        List<EmployeeFullInfo> createFile = employeeRepository.getFullInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonFile = objectMapper.writeValueAsString(createFile);
        Report report = new Report();
        report.setFileReport(jsonFile.getBytes());
        reportRepository.save(report);
    }

    @Override
    public void downloadFile(Integer id) throws IOException {
        Optional<Report> foundFile = reportRepository.findById(id);
        try (FileOutputStream fileOutputStream = new FileOutputStream(String.valueOf(foundFile));
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
             objectOutputStream.writeObject(foundFile);
        }
    }
}

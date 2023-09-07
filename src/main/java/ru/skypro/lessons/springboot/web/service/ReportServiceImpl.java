package ru.skypro.lessons.springboot.web.service;

import io.github.classgraph.Resource;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.web.model.Report;
import ru.skypro.lessons.springboot.web.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.web.repository.ReportRepository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Integer reportToFile() {
        Report createFile = (Report) EmployeeRepository.getFullInfo();
        return reportRepository.save(createFile).getId();
    }

    @Override
    public Resource downloadFile(Integer id) throws IOException {
        Optional<Report> foundFile = reportRepository.findById(id);
        try (FileOutputStream fileOutputStream = new FileOutputStream(String.valueOf(foundFile));
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
             objectOutputStream.writeObject(foundFile);
        }
    }
}

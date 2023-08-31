package ru.skypro.lessons.springboot.web.service;

import io.github.classgraph.Resource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.web.model.Report;
import ru.skypro.lessons.springboot.web.repository.ReportRepository;

import java.io.IOException;
import java.util.Collections;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    @Query(value = "CREATE TABLE report (" +
            "departmentName STRING NOT NULL," +
            "quantityEmployees INTEGER NOT NULL" +
            "maxSalary INTEGER NOT NULL" +
            "minSalary INTEGER NOT NULL" +
            "averageSalary INTEGER NOT NULL)",
            nativeQuery = true)
    public Integer reportToFile(Report report) {
        Report createFile = (Report) reportRepository.findAll((Pageable) report);
        return createFile.getId();
    }

    @Override
    public ResponseEntity<Resource> downloadFile(Integer id) throws IOException {
        Report foundFile = (Report) reportRepository.findAllById(Collections.singleton(id));
        ByteArrayResource resource = new ByteArrayResource(foundFile.getFileReport());
        return reportRepository.downloadFile(resource.getFile());
    }
}

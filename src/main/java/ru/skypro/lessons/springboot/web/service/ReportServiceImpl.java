package ru.skypro.lessons.springboot.web.service;

import io.github.classgraph.Resource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.web.model.Report;
import ru.skypro.lessons.springboot.web.repository.ReportRepository;

import java.io.IOException;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Integer reportToFile() {
        Report createFile = (Report) reportRepository.findAll();
        return createFile.getId();
    }

    @Override
    public ResponseEntity<Resource> downloadFile(Integer id) throws IOException {
        Optional<Report> foundFile = reportRepository.findById(id);
        ByteArrayResource resource = new ByteArrayResource(foundFile.get().getFileReport());
        return reportRepository.downloadFile(resource.getByteArray());
    }
}

package ru.skypro.lessons.springboot.web.controller;

import io.github.classgraph.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.web.service.ReportService;

import java.io.IOException;

@RestController
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping(value = "/")
    public Integer reportToFile() {
        return reportService.reportToFile();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> downloadFile(@PathVariable("id") Integer id) throws IOException {
        return reportService.downloadFile(id);
    }
}

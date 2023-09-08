package ru.skypro.lessons.springboot.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.classgraph.Resource;
import org.springframework.http.MediaType;
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
    public Integer reportToFile() throws JsonProcessingException {
        return reportService.reportToFile();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Resource downloadFile(@PathVariable("id") Integer id) throws IOException {
        return reportService.downloadFile(id);
    }
}

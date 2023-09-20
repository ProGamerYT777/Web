package ru.skypro.lessons.springboot.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
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

    @PostMapping(value = "/admin/")
    public void reportToFile() throws JsonProcessingException {
        reportService.reportToFile();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void downloadFile(@PathVariable("id") Integer id) throws IOException {
        reportService.downloadFile(id);
    }
}

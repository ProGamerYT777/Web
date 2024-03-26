package ru.skypro.lessons.springboot.web.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.web.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.web.mapper.EmployeeMapper;
import ru.skypro.lessons.springboot.web.model.Report;
import ru.skypro.lessons.springboot.web.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.web.repository.ReportRepository;
import ru.skypro.lessons.springboot.web.service.EmployeeService;
import ru.skypro.lessons.springboot.web.service.ReportService;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;
    Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

    public ReportServiceImpl(ReportRepository reportRepository, EmployeeRepository employeeRepository, EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.reportRepository = reportRepository;
        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public Integer createReport() throws IOException {
        logger.debug("Метод создания Report");
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("Report.json");
        Report report = new Report();
        report.setId(1);
        String json = objectMapper.writeValueAsString(reportRepository.createReport());
        Files.writeString(Paths.get(file.getName()), json);

        report.setFile(file.getPath());
        reportRepository.save(report);
        report.setFile(report.getId() + file.getPath());
        return report.getId();


    }


    @Override
    public void upload(File file) throws IOException {
        logger.debug("Метод загрузки сотрудников из файла.");
        ObjectMapper objectMapper = new ObjectMapper();
        List<EmployeeDTO> employeeDTOS = objectMapper.readValue(file, new TypeReference<>() {
        });
        employeeService.addEmployee(employeeDTOS);

    }

    @Override
    public Report getReportById(int id) {
        logger.debug("Метод поиска репорта по id {}", id);
        return reportRepository.findById(id)
                .orElseThrow();
    }

    private static String readTextFromFile(String fileName) {
        Path path = Paths.get(fileName);
        try {
            return Files.lines(path)
                    .collect(Collectors.joining());
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return "";
        }
    }

    private static void writeTextToFile(String text, String fileName) {
        Path path = Paths.get(fileName);
        try {
            Files.write(path, text.getBytes(StandardCharsets.UTF_8));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}

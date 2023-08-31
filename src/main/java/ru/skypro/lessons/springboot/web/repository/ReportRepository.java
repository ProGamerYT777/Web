package ru.skypro.lessons.springboot.web.repository;

import io.github.classgraph.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.web.model.Report;

import java.io.File;
import java.io.IOException;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

    Integer reportToFile(Report report);

    ResponseEntity<Resource> downloadFile(File report) throws IOException;
}

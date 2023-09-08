package ru.skypro.lessons.springboot.web.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.classgraph.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.web.model.Report;

import java.io.IOException;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
    @Query(value = "SELECT report.*)",
            nativeQuery = true)
    Integer reportToFile() throws JsonProcessingException;

    Resource downloadFile(byte[] report) throws IOException;
}

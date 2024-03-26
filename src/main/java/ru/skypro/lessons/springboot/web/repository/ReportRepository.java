package ru.skypro.lessons.springboot.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.web.dto.ReportDTO;
import ru.skypro.lessons.springboot.web.model.Report;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Integer> {
    @Query("SELECT new ru.skypro.lessons.springboot.web.dto.ReportDTO(p.id, count(e.id), max(e.salary), min(e.salary), avg(e.salary)) " +
            "FROM Employee e left join Position p on p.id = e.position.id " +
            "GROUP BY p.id")
    List<ReportDTO> createReport();


}

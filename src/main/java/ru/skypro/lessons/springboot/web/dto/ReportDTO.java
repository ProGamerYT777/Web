package ru.skypro.lessons.springboot.web.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.skypro.lessons.springboot.web.model.Report;

import java.util.Arrays;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class ReportDTO {
    private Integer position;
    private Long countEmployees;
    private Integer maxSalary;
    private Integer minSalary;
    private Double avgSalary;

    public ReportDTO(Integer pos, Long count, Integer max, Integer min, Double avg) {
        this.position = pos;
        this.countEmployees = count;
        this.maxSalary = max;
        this.minSalary = min;
        this.avgSalary = avg;
    }
}

package ru.skypro.lessons.springboot.web.dto;


import ru.skypro.lessons.springboot.web.model.Report;

import java.util.Arrays;

public class ReportDTO {
    private Integer id;
    private byte[] fileReport;

    public static ReportDTO fromReport(Report report) {
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setId(report.getId());
        reportDTO.setFileReport(report.getFileReport());
        return reportDTO;
    }

    public Report toReport() {
        Report report = new Report();
        report.setId(this.getId());
        report.setFileReport(this.getFileReport());
        return report;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getFileReport() {
        return fileReport;
    }

    public void setFileReport(byte[] fileReport) {
        this.fileReport = fileReport;
    }

    @Override
    public String toString() {
        return "ReportDTO{" +
                "id=" + id +
                ", fileReport=" + Arrays.toString(fileReport) +
                '}';
    }
}

package ru.skypro.lessons.springboot.web.model;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
@Table(name = "report")
public class Report implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private byte[] fileReport;

    public Report() {
    }

    public Report(Integer id, byte[] fileReport) {
        this.id = id;
        this.fileReport = fileReport;
    }
    public Integer getId() {
        return this.id;
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
}

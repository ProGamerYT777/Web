package ru.skypro.lessons.springboot.web.model;

import jakarta.persistence.*;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.AbstractPersistable;

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
    public Integer getId(SingularAttribute<AbstractPersistable, Serializable> id) {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getFileReport(Integer id) {
        return fileReport;
    }

    public void setFileReport(byte[] fileReport) {
        this.fileReport = fileReport;
    }
}

package ru.skypro.lessons.springboot.web.model;

import java.io.Serializable;

public class Report implements Serializable {
    private String departmentName;
    private int quantityEmployees;
    private int maxSalary;
    private int minSalary;
    private int averageSalary;

    public Report() {
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getQuantityEmployees() {
        return quantityEmployees;
    }

    public void setQuantityEmployees(int quantityEmployees) {
        this.quantityEmployees = quantityEmployees;
    }

    public int getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(int maxSalary) {
        this.maxSalary = maxSalary;
    }

    public int getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(int minSalary) {
        this.minSalary = minSalary;
    }

    public int getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(int averageSalary) {
        this.averageSalary = averageSalary;
    }

    @Override
    public String toString() {
        return "Report{" +
                "departmentName='" + departmentName + '\'' +
                ", quantityEmployees=" + quantityEmployees +
                ", maxSalary=" + maxSalary +
                ", minSalary=" + minSalary +
                ", averageSalary=" + averageSalary +
                '}';
    }
}

package ru.skypro.lessons.springboot.web.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Objects;

@Entity
@Table(name = "employee")
@Getter
@Setter
@Accessors(chain = true)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private  String name;
    private  int salary;
    @ManyToOne
    private Position position;

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;

    }
    public Employee() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee1 = (Employee) o;
        return salary == employee1.salary && Objects.equals(id, employee1.id) && Objects.equals(name, employee1.name) && Objects.equals(position, employee1.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, salary, position);
    }
}

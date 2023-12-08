package ru.skypro.lessons.springboot.web.dto;

import ru.skypro.lessons.springboot.web.model.Employee;
import ru.skypro.lessons.springboot.web.model.Position;

public class EmployeeDTO {
    private Integer id;
    private String name;
    private int salary;
    private Position position;
    private int count = 0;

    public EmployeeDTO( String name, Integer salary, Position position) {
        this.id = count++;
        this.name = name;
        this.salary = salary;
        this.position = position;
    }

    public EmployeeDTO() {

    }

    public static EmployeeDTO fromEmployee(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setSalary(employee.getSalary());
        var position = employee.getPosition();
        var positionDto = new Position(position.getId(), position.getName());
        employeeDTO.setPosition(positionDto);
        return employeeDTO;
    }

    public Employee toEmployee() {
        Employee employee = new Employee();
        employee.setId(this.getId());
        employee.setName(this.getName());
        employee.setSalary(this.getSalary());
        return employee;
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
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}


package ru.skypro.lessons.springboot.web.constants;

import ru.skypro.lessons.springboot.web.model.Employee;
import ru.skypro.lessons.springboot.web.model.Position;

import java.util.ArrayList;
import java.util.List;

public class EmployeeConstants {
    public static void EmployeesTest(){
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1, "Anton", 100000, new Position(1, "developer")));
        employeeList.add(new Employee(2, "Anna", 90000, new Position(2, "tester")));
        employeeList.add(new Employee(3, "Leonid", 1100000, new Position(1, "developer")));
    }

}

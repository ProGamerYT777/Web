package ru.skypro.lessons.springboot.web.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.web.model.Employee;
import ru.skypro.lessons.springboot.web.model.EmployeeFullInfo;

import java.awt.print.Pageable;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

@Service
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final ArrayList<Employee> employeeList = new ArrayList<>();
    @PostConstruct
    private void listElements() {
        employeeList.add(new Employee(1,"Ivan", 30000));
        employeeList.add(new Employee(2,"Oleg", 20000));
        employeeList.add(new Employee(3,"Olga", 25000));
        employeeList.add(new Employee(4,"Anton", 40000));
    }

    @Override
    public double sumSalariesEmployees() {
        return employeeList.stream().mapToDouble(Employee::getSalary).sum();
    }

    @Override
    public Employee minSalaryEmployee() {
       return employeeList.stream().min(Comparator.comparing(Employee::getSalary)).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Employee maxSalaryEmployee() {
        return employeeList.stream().max(Comparator.comparing(Employee::getSalary)).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Employee> highAverageSalariesEmployees() {
        double averageSallary = sumSalariesEmployees() / employeeList.size();
            return employeeList.stream().filter (e -> e.getSalary() >= averageSallary).collect (Collectors.toList());
    }

    @Override
    public void createEmployee(Employee employee) {
        employeeList.add(employee);
    }

    @Override
    public void updateEmployeeById(Employee employee) {
        Employee oldEmployee = getEmployeeById(employee.getId());
        if (oldEmployee != null) {
            oldEmployee.setName(employee.getName());
            oldEmployee.setSalary(employee.getSalary());
        }
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        try {
            return employeeList.stream().filter(employee -> employee.getId() == id).findFirst().orElseThrow();
        } catch (Exception e) {
            System.out.println("Нет сотрудника с id: " + id);
            return null;
        }
    }

    @Override
    public void deleteById(Integer id) {
        employeeList.remove(getEmployeeById(id));
    }

    @Override
    public List<Employee> employeesSalaryHighThan(int salary) {
        return employeeList.stream().filter(i -> i.getSalary() > salary).toList();
    }

    @Override
    public List<Employee> getEmployeeWithHighestSalary() {
        return Collections.singletonList(employeeList.stream().collect(
                collectingAndThen(
                        maxBy(Comparator.comparingDouble(e -> e.getSalary())), Optional::get
                )
        ));
    }

    @Override
    public List<Employee> getEmployeesByPositionLike(String position) {
        return null;
    }

    @Override
    public List<EmployeeFullInfo> getFullInfo(Integer id) {
        return null;
    }

    @Override
    public List<Employee> getPageInfo(int pageIndex, int unitPerPage) {
         return employeeList;
    }
}

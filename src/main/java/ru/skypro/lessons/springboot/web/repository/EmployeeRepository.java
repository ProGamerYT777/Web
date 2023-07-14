package ru.skypro.lessons.springboot.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.web.model.Employee;
import ru.skypro.lessons.springboot.web.model.EmployeeFullInfo;
import ru.skypro.lessons.springboot.web.model.Position;

import java.util.List;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query (value = "SELECT * " +
            "FROM Employee e " +
            "WHERE salary = (SELECT MAX(salary) FROM Employee e2)",
             nativeQuery = true)
    List<Employee> getEmployeeWithHighestSalary();
    List<Employee> getEmployeesByPositionLike(Position position);

    @Query("SELECT e.name , e.salary , p.name " +
            "FROM Employee e join fetch Position p " +
            "WHERE e.id = ?1 and e.position = p")
    List<EmployeeFullInfo> getFullInfo(Integer id);
}

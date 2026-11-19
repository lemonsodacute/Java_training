package com.example.employeemanagement.repository;

import com.example.employeemanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByNameContainingIgnoreCase(String name);
    List<Employee> findByDepartment_Id(Long departmentId);
    List<Employee> findByNameContainingIgnoreCaseAndDepartment_Id(String name, Long departmentId);
    boolean existsByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);

    @Query("select e.department.id as departmentId, e.department.name as departmentName, count(e.id) as totalEmployees " +
           "from Employee e group by e.department.id, e.department.name")
    List<DepartmentCount> countByDepartment();

    interface DepartmentCount {
        Long getDepartmentId();
        String getDepartmentName();
        Long getTotalEmployees();
    }
}

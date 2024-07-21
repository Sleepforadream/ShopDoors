package com.shopdoors.dao.repository.user;

import com.shopdoors.dao.entity.user.Employee;
import com.shopdoors.dao.enums.user.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<List<Employee>> findEmployeesByPosition(Position position);
}
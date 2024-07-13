package com.shopdoors.dao.repository;

import com.shopdoors.dao.entity.Employee;
import com.shopdoors.dao.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<List<Employee>> findEmployeesByPosition(Position position);
}
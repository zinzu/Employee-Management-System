package com.example.demo.service;

import com.example.demo.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService {
    List<Employee> findAll();

    Optional<Employee> findById(int id);

    void save(Employee theEmployee);

    Employee deleteById(int id);
}

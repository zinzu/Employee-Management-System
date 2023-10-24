package com.example.demo.service;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {

        List<Employee> theEmployee = employeeRepository.findAllByOrderByLastName();
        return theEmployee;
    }

    @Override
    public Optional<Employee> findById(int id) {
        Optional<Employee> theEmployee = employeeRepository.findById(id);
        return theEmployee;

    }

    @Override
    public void save(Employee theEmployee) {

        employeeRepository.save(theEmployee);
    }

    @Override
    public Employee deleteById(int id) {
        employeeRepository.deleteById(id);
        return null;
    }
}

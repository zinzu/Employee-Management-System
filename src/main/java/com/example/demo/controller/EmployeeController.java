package com.example.demo.controller;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String employeeList(Model theModel){

        List<Employee> theEmployee = employeeService.findAll();

          theModel.addAttribute("employees",theEmployee);

        return "employees/employee-list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        Employee theEmployee = new Employee();

        theModel.addAttribute("employee",theEmployee);

        return "employees/employee-form";
    }

    @GetMapping("/showFormForUpdate/{employeeId}")
    public String showFormForUpdate(@PathVariable("employeeId") int id, Model theModel){

        Optional<Employee> theEmployee = employeeService.findById(id);

        theModel.addAttribute("employee",theEmployee);

        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){

         employeeService.save(theEmployee);

        return "redirect:/employees/list";
    }

    @GetMapping("/delete/{employeeId}")
    public String delete(@PathVariable("employeeId") int id, Model theModel){

        Employee theEmployee = employeeService.deleteById(id);

        theModel.addAttribute("employee",theEmployee);

        return "redirect:/employees/list";
    }

}

package com.cano.cruddemo.rest;

import com.cano.cruddemo.entity.Employee;
import com.cano.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/employees/{theId}")
    public Employee findById(@PathVariable int theId){
        Employee employee = employeeService.findById(theId);

        if (employee == null) {
            throw new RuntimeException("Employee id not found ->" + theId);
        }

        return employee;
    }

    @PostMapping("/employees")
    public Employee save(@RequestBody Employee employee){
        employee.setId(0);

        return employeeService.save(employee);
    }
}

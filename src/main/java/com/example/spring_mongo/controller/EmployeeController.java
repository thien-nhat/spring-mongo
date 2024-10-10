package com.example.spring_mongo.controller;

import com.example.spring_mongo.model.Employee;
import com.example.spring_mongo.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        return employeeOptional.map(employee -> ResponseEntity.ok().body(employee)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeRepository.save(employee), HttpStatus.CREATED);
    }
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
                                                   @RequestBody Employee employeeDetails) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        return employeeOptional.map(employee -> {
            employee.setEmail(employeeDetails.getEmail());
            employee.setLastName(employeeDetails.getLastName());
            employee.setFirstName(employeeDetails.getFirstName());
            final Employee updatedEmployee = employeeRepository.save(employee);
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable(value = "id") Long employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        return employeeOptional.map(employee -> {
            employeeRepository.delete(employee);
            return new ResponseEntity<>(employee, HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}

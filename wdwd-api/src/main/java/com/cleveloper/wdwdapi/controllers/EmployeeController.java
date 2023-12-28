package com.cleveloper.wdwdapi.controllers;

import com.cleveloper.wdwdapi.exception.ResourceNotFoundException;
import com.cleveloper.wdwdapi.models.Employee;
import com.cleveloper.wdwdapi.repository.EmployeeRepository;
import com.cleveloper.wdwdapi.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id")  Long id) throws ResourceNotFoundException {
        Employee employee =  employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found for this id :: " + id)
        );
        return ResponseEntity.ok().body(employee);
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        employee.setId(sequenceGeneratorService.generateSequence(Employee.SEQUENCE_NAME));
        return employeeRepository.save(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long id,
                                                   @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found for this id :: " + id)
        );
        employee.setEmailId(employeeDetails.getEmailId());
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found for this id :: " + id)
        );
        employeeRepository.deleteById(id);
        return Map.of("deleted", Boolean.TRUE);
    }
}

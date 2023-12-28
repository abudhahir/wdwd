package com.cleveloper.wdwdapi.repository;

import com.cleveloper.wdwdapi.models.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, Long> {
}

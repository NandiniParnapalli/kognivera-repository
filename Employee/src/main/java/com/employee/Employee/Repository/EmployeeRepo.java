package com.employee.Employee.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.employee.Employee.DTO.Employee;

public interface EmployeeRepo extends MongoRepository<Employee, Integer> {

}

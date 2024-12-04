package com.springbatch.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbatch.dto.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
	
	

}

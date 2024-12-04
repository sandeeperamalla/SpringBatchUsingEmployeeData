package com.springbatch.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springbatch.dto.Employee;
import com.springbatch.repo.EmployeeRepo;

@Repository
public class EmployeeDao {

	@Autowired
	EmployeeRepo employeeRepo;
	
	
	public void saveEmployeeDataDao(Employee employee) {
	employeeRepo.save(employee);
	}

}

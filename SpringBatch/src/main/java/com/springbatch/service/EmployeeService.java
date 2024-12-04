package com.springbatch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbatch.dao.EmployeeDao;
import com.springbatch.dto.Employee;

@Service
public class EmployeeService {
		
	@Autowired
	EmployeeDao dao;
	
	
	public void saveEmployeeDataService(Employee employee) {
		dao.saveEmployeeDataDao(employee);
		
	}
}

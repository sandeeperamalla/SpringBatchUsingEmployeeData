package com.springbatch.config;

import org.springframework.batch.item.ItemProcessor;

import com.springbatch.dto.Employee;

public class EmployeeProcessor implements ItemProcessor<Employee, Employee> {

	@Override
	public Employee process(Employee employee) throws Exception {
		employee.setFirstName(employee.getFirstName().toUpperCase());
		employee.setLastName(employee.getLastName().toUpperCase());
		return employee;
	}

}

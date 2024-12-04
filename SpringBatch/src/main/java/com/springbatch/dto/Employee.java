package com.springbatch.dto;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Employee {

	// The unique identifier for each employee in the database
	@Id
	private int id;

	// The first name of the employee
	private String firstName;

	// The last name of the employee
	private String lastName;

	// The email address of the employee
	private String email;

	// The department the employee belongs to
	private String department;

	// The job position of the employee
	private String position;

	// The salary of the employee
	private BigDecimal salary;

	// The hire date of the employee, represented as a string (consider converting
	// it to a Date type if needed)
	private String hireDate;

	// The performance rating of the employee, represented as a BigDecimal (e.g.,
	// 4.5)
	private BigDecimal performanceRating;

	// The number of years of experience the employee has
	private BigDecimal yearsOfExperience;

	// The age of the employee
	private int age;

	// The phone number of the employee
	private String phoneNumber;

	// The location where the employee is based (e.g., city, state)
	private String location;

	// A string containing the employee's skills (consider using a more structured
	// format or breaking it into a separate table if there are multiple skills)
	private String skills;

	// The education level of the employee (e.g., Bachelor's, Master's)
	private String educationLevel;

}

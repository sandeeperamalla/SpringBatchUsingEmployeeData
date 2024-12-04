package com.springbatch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbatch.service.EmployeeService;

@RestController
public class EmployeeController {

    // Autowiring the EmployeeService to handle business logic related to Employee.
    @Autowired
    EmployeeService service;

    // Autowiring the JobLauncher to launch batch jobs.
    @Autowired
    private JobLauncher jobLauncher;

    // Autowiring the Job bean that represents the batch job.
    @Autowired
    private Job job;

	

	@PostMapping("/importData")
	public String jobLauncherController() {

	    // Creating job parameters to be passed to the job, including a timestamp of when the job starts.
	    final JobParameters jobParameters = new JobParametersBuilder()
	            .addLong("startAt", System.currentTimeMillis())  // Adding the current timestamp as a job parameter
	            .toJobParameters();  // Converting parameters to a JobParameters object

	    try {
	        // Launch the job with the provided parameters
	        final JobExecution jobExecution = jobLauncher.run(job, jobParameters);

	        // Return the status of the job (e.g., COMPLETED, FAILED)
	        return jobExecution.getStatus().toString();  // Converts the job execution status to a string (e.g., "COMPLETED")
	        
	    } catch (JobExecutionAlreadyRunningException | JobRestartException 
	             | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
	        // Handle exceptions that may occur during job execution
	        e.printStackTrace();  // Print the stack trace for debugging purposes
	        
	        // Return a failure message with the exception details
	        return "Job failed with exception: " + e.getMessage();  // Returning the exception message
	    }
	}

}
package com.springbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.springbatch.dto.Employee;
import com.springbatch.repo.EmployeeRepo;

@Configuration
public class SpringBatchProcessing {

    @Autowired
    EmployeeRepo repo;  // Repository for persisting Employee entities.

    /**
     * Bean definition for EmployeeProcessor. 
     * This processor will be used to apply any custom business logic on each Employee record.
     */
    @Bean
    EmployeeProcessor processor() {
        return new EmployeeProcessor();  // Returns the processor for transforming Employee data.
    }

    /**
     * Bean definition for FlatFileItemReader.
     * This reader will read Employee data from the EmployeeData.csv file and map it to Employee objects.
     */
    @Bean
    public FlatFileItemReader<Employee> reader() {
        return new FlatFileItemReaderBuilder<Employee>()
                .name("employeeItemReader")  // Reader name for logging purposes
                .resource(new ClassPathResource("EmployeeData.csv"))  // Resource (CSV file) from classpath
                .linesToSkip(1)  // Skip the header line in the CSV file
                .lineMapper(lineMapper())  // Use lineMapper to parse each line of the file
                .targetType(Employee.class)  // Target type for mapping (Employee class)
                .build();
    }

    /**
     * LineMapper defines how each line in the CSV file will be mapped to the Employee object.
     * This involves setting up a tokenizer (to split the line into fields) and a field set mapper (to map those fields to Employee fields).
     */
    private LineMapper<Employee> lineMapper() {
        DefaultLineMapper<Employee> lineMapper = new DefaultLineMapper<>();  // Default mapper that maps lines to the target type (Employee).

        // Tokenizer used to split the CSV line into tokens based on the delimiter (comma in this case)
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");  // Delimiter used in the CSV (comma)
        lineTokenizer.setStrict(false);  // Disable strict mode to allow flexibility in the CSV format (like extra fields or missing fields)
        
        // Defining the expected field names in the CSV (which should match the Employee attributes)
        lineTokenizer.setNames("id", "firstName", "lastName", "email", "department", "position", 
                               "salary", "hireDate", "performanceRating", "yearsOfExperience", "age",
                               "phoneNumber", "location", "skills", "educationLevel");

        // The fieldSetMapper is responsible for mapping the fields from the tokenizer to the Employee object
        BeanWrapperFieldSetMapper<Employee> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Employee.class);  // Map the fields to the Employee class

        // Set the tokenizer and field mapper in the lineMapper
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;  // Return the fully configured lineMapper
    }

    /**
     * RepositoryItemWriter writes each Employee to the database using the save method of the EmployeeRepo.
     */
    @Bean
    RepositoryItemWriter<Employee> writer() {
        RepositoryItemWriter<Employee> writer = new RepositoryItemWriter<>();
        writer.setRepository(repo);  // Set the repository for saving the Employee object
        writer.setMethodName("save");  // Define the method to call for saving the Employee
        return writer;  // Return the writer
    }

    /**
     * Job definition for batch processing.
     * It ties the job with the job repository and defines the steps to execute.
     */
    @Bean
    public Job job(JobRepository jobRepository, Step step) {
        return new JobBuilder("importEmployee", jobRepository)  // Create a new job called "importEmployee"
                .start(step)  // Define the first step in the job
                .build();  // Build the job
    }

    /**
     * Step definition for batch processing.
     * This step reads, processes, and writes Employee data in chunks of 10 records at a time.
     */
    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager manager) {
        return new StepBuilder("csv-step", jobRepository)  // Step definition with name "csv-step"
                .<Employee, Employee>chunk(10, manager)  // Process 10 records at a time in chunks
                .reader(reader())  // Use the reader to read Employee data
                .processor(processor())  // Use the processor to process each Employee record
                .writer(writer())  // Use the writer to persist the Employee data
                .build();  // Build the step
    }
}


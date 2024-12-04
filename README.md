## **SpringBatchUsingEmployeeData**
This project demonstrates how to use **Spring Batch 5.0** for batch processing in a Spring Boot application. The application reads employee data from a CSV file (`EmployeeData.csv`), processes each record, and stores the data into a database using Spring Data JPA. The batch job can be triggered via a REST API endpoint, making it easy to import employee data into the system.

With this setup, you can efficiently process large sets of data, such as employee records, in a production-grade manner with error handling, transaction management, and performance optimizations provided by Spring Batch.

___

## **Application Configuration**

- **Employee Entity**: Represents the data structure of the employee being processed.
- **EmployeeRepo**: Interface extending `JpaRepository` for CRUD operations on `Employee` data.
- **Spring Batch Job**: Configures the `FlatFileItemReader`, custom `EmployeeProcessor`, and `RepositoryItemWriter`.

___

## **Batch Job Flow**

The job follows this process:

1. **Reader** (`FlatFileItemReader`): Reads data from `EmployeeData.csv` located in the `src/main/resources` directory.
2. **Processor** (`EmployeeProcessor`): Processes each employee record, potentially transforming or validating the data.
3. **Writer** (`RepositoryItemWriter`): Writes the processed data to the database using the `EmployeeRepo`.

___

## **Project Setup**

# **Prerequisites**

- Java 11 or higher
- Spring Boot 3.0+ (with Spring Batch 5.0)
-  An IDE (e.g., IntelliJ IDEA, Eclipse) or terminal to run the project
- Maven
- A relational database ( MySQL)

___
  

# **Dependencies**

- Spring Batch
- Spring Boot Starter Data JPA
- Spring Boot Starter Web
- H2 Database (for testing or replace with your preferred database)


___


### **Clone the Repository**

Start by cloning the repository to your local machine:

```bash
git clone https://github.com/sandeeperamalla/SpringBatchUsingEmployeeData.git
cd SpringBatchUsingEmployeeData
```
___

### **Conclusion**
-This project showcases how to use Spring Batch 5.0 to process data in a batch mode, read CSV files, and store the data into a database with Spring Data JPA. You can extend this project by adding more complex 
 processing, error handling, and scheduling.

___

## **License**
-This project is licensed under the MIT License - see the LICENSE file for details.




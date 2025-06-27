package com.streamapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeData {

    public static List<Employee> getEmployees() {
        List<Employee> list = new ArrayList<>();

        list.add(new Employee(1, "John", "Doe", "IT", "Developer", 75000, "john.doe@example.com", "1234567890", 28, true));
        list.add(new Employee(2, "Jane", "Smith", "HR", "Recruiter", 50000, "jane.smith@example.com", "1234509876", 32, true));
        list.add(new Employee(3, "Mike", "Johnson", "Finance", "Analyst", 65000, "mike.johnson@example.com", "9876543210", 29, false));
        list.add(new Employee(4, "Emily", "Davis", "IT", "Tester", 55000, "emily.davis@example.com", "9988776655", 27, false));
        list.add(new Employee(5, "Robert", "Brown", "Sales", "Executive", 48000, "robert.brown@example.com", "8899001122", 35, true));
        list.add(new Employee(6, "Linda", "Wilson", "Marketing", "Manager", 88000, "linda.wilson@example.com", "7766554433", 42, true));
        list.add(new Employee(7, "James", "Taylor", "IT", "Architect", 105000, "james.taylor@example.com", "6655443322", 38, true));
        list.add(new Employee(8, "Patricia", "Anderson", "HR", "Executive", 47000, "patricia.anderson@example.com", "5566778899", 26, false));
        list.add(new Employee(9, "David", "Thomas", "Finance", "Lead", 92000, "david.thomas@example.com", "4455667788", 45, true));
        list.add(new Employee(10, "Barbara", "Jackson", "IT", "Developer", 72000, "barbara.jackson@example.com", "3344556677", 31, false));
        list.add(new Employee(11, "Kevin", "White", "Support", "Engineer", 49000, "kevin.white@example.com", "2233445566", 29, false));
        list.add(new Employee(12, "Susan", "Harris", "Marketing", "Executive", 58000, "susan.harris@example.com", "1122334455", 36, true));
        list.add(new Employee(13, "Brian", "Martin", "Sales", "Lead", 87000, "brian.martin@example.com", "6677889900", 39, true));
        list.add(new Employee(14, "Jessica", "Thompson", "IT", "Manager", 99000, "jessica.thompson@example.com", "7788990011", 41, true));
        list.add(new Employee(15, "Steven", "Garcia", "Finance", "Clerk", 39000, "steven.garcia@example.com", "8899112233", 24, false));
        list.add(new Employee(16, "Karen", "Martinez", "HR", "Manager", 81000, "karen.martinez@example.com", "9001122334", 37, true));
        list.add(new Employee(17, "George", "Robinson", "Support", "Technician", 43000, "george.robinson@example.com", "3216549870", 30, false));
        list.add(new Employee(18, "Nancy", "Clark", "IT", "Developer", 76000, "nancy.clark@example.com", "4321654987", 28, true));
        list.add(new Employee(19, "Edward", "Rodriguez", "Sales", "Executive", 46000, "edward.rodriguez@example.com", "5678901234", 33, false));
        list.add(new Employee(20, "Lisa", "Lewis", "Marketing", "Lead", 93000, "lisa.lewis@example.com", "6789012345", 40, true));

        return list;
    }

    public static final Map<Integer, List<String>> employeeSkills = new HashMap<>();

    static {
        // Preload data in static block
        employeeSkills.put(1, Arrays.asList("Java", "Spring", "SQL"));
        employeeSkills.put(2, Arrays.asList("HR", "Recruitment", "Interviewing"));
        employeeSkills.put(3, Arrays.asList("Excel", "Finance", "PowerBI", "SQL"));
        employeeSkills.put(4, Arrays.asList("Testing", "Selenium", "Postman"));
        employeeSkills.put(5, Arrays.asList("Sales", "Negotiation", "CRM"));
        employeeSkills.put(6, Arrays.asList("Java", "Spring Boot", "Microservices", "Kafka"));
        employeeSkills.put(7, Arrays.asList("Cloud", "AWS", "Terraform", "Docker"));
        employeeSkills.put(8, Arrays.asList("React", "JavaScript", "UI/UX"));
        employeeSkills.put(9, Arrays.asList("SQL", "Data Modeling", "ETL"));
        employeeSkills.put(10, Arrays.asList("Project Management", "Scrum", "JIRA"));
        employeeSkills.put(11, Arrays.asList("Networking", "Linux", "AWS"));
        employeeSkills.put(12, Arrays.asList("Marketing", "Email Campaigns", "SEO"));
        employeeSkills.put(13, Arrays.asList("Java", "JUnit", "Mockito"));
        employeeSkills.put(14, Arrays.asList("Spring", "Spring Boot", "Docker"));
        employeeSkills.put(15, Arrays.asList("Data Analysis", "Python", "PowerBI"));
        employeeSkills.put(16, Arrays.asList("Python", "Machine Learning", "Pandas", "NumPy"));
        employeeSkills.put(17, Arrays.asList("NoSQL", "MongoDB", "Redis"));
        employeeSkills.put(18, Arrays.asList("DevOps", "CI/CD", "Kubernetes", "Helm"));
        employeeSkills.put(19, Arrays.asList("Customer Service", "Communication", "CRM"));
        employeeSkills.put(20, Arrays.asList("Documentation", "Technical Writing", "MS Word"));
    }

    // Getter
    public static Map<Integer, List<String>> getEmployeeSkills() {
        return employeeSkills;
    }

}
package com.streamapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Comparable and Comparator QnA" );

        List<Employee> employees = EmployeeData.getEmployees();
        // Sort by Salary using Comparable
        Collections.sort(employees);
        // employees.forEach(System.out::println);

        // Sort by age descending
        employees.sort(Comparator.comparingInt(Employee::getAge).reversed());
        // employees.forEach(System.out::println);

        // Sort by department then salary
        employees.sort(Comparator.comparing(Employee::getDepartment).thenComparing(Employee::getSalary));

        // Sort by Last name
        employees.sort(Comparator.comparing(e -> e.getLastName().toLowerCase()));
        // employees.forEach(System.out::println);

        // Top 5 Highest paid
        employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).limit(5).forEach(System.out::println);

        List<Employee> itEmployees = employees.stream().filter(e -> "IT".equalsIgnoreCase(e.getDepartment()))
                                                       .collect(Collectors.toList());

        List<String> emails = employees.stream().map(Employee::getEmail).collect(Collectors.toList());

        // Count Permanent vs Contract Employees
        Map<Boolean, List<Employee>> partitioned = employees.stream().collect(Collectors.partitioningBy(Employee::isPermanent));

        System.out.println("Permanent: ");
        partitioned.get(true).forEach(System.out::println);

        System.out.println("Contract: ");
        partitioned.get(false).forEach(System.out::println);

        // Get Average Salary of Employees
        double avgSalary = employees.stream().collect(Collectors.averagingDouble(Employee::getSalary));

        // Group Employees by Department:
        Map<String, List<Employee>> groupEmpByDept = employees.stream() 
                                                         .collect(Collectors.groupingBy(Employee::getDepartment));
        groupEmpByDept.forEach((dept, emp) -> {
            System.out.println(dept + " : ");
            emp.forEach(System.out::println);
        });

        // Employee with Maximum salary
        Employee maxSalaryEmp = employees.stream().max(Comparator.comparingDouble(Employee::getSalary)).orElse(null);

        // Names of All Employees Older than 30
        List<String> empOlderThan30 = employees.stream().filter(emp -> emp.getAge() > 30).map(Employee::getFirstName)
                                                        .collect(Collectors.toList());
        System.out.println(empOlderThan30);

        // or we can also
        List<String> empBetn30and45 = employees.stream().filter(emp -> emp.getAge() > 30 && emp.getAge() < 45)
                                                .map(emp -> emp.getFirstName() + " " + emp.getLastName())
                                                .collect(Collectors.toList());
        System.out.println(empBetn30and45);
                                       
        // Get all unique departments
        Set<String> departments = employees.stream().map(Employee::getDepartment).collect(Collectors.toSet());
        System.out.println(departments);

        // Check if any employee is under 25
        boolean anyUnder25 = employees.stream().anyMatch(emp -> emp.getAge() < 25);
        System.out.println(anyUnder25);
// ___________________________________________________________________________________________________________________________________
        // FLATMAP Practice
        List<List<String>> allSkillLists = new ArrayList<>(EmployeeData.getEmployeeSkills().values());
        
        // Skills which starts from A
        Set<String> skillsStartingWithA = allSkillLists.stream().flatMap(List::stream).filter(skill -> skill.toLowerCase().startsWith("a")).collect(Collectors.toSet());
        System.out.println(skillsStartingWithA);

        // Unique skills
        Set<String> uniSkills = allSkillLists.stream().flatMap(List::stream).collect(Collectors.toSet());
        System.out.println(uniSkills);

        // Sorted list of all skills
        List<String> sortedSkills = allSkillLists.stream().flatMap(List::stream).distinct().sorted().collect(Collectors.toList());

        // Count occurrences of Each skill
        Map<String, Long> freqMap = allSkillLists.stream().flatMap(List::stream).collect(Collectors.groupingBy(skill -> skill,
                                                                                        Collectors.counting()));
        freqMap.forEach((k, v) ->
            System.out.println(k + " : " + v));                                                                                
        
    // Combine Employee Name with Their Skills
        employees.stream().filter(e -> EmployeeData.employeeSkills.containsKey(e.getId()))
                          .forEach(e -> {
                                System.out.println(e.getFirstName() + " : " + EmployeeData.employeeSkills.get(e.getId()));
                          });

    }
}

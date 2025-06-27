package com.streamapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Comparable<Employee> {

    private int id;
    private String firstName;
    private String lastName;
    private String department;
    private String designation;
    private double salary;
    private String email;
    private String phoneNumber;
    private int age;
    private boolean isPermanent;

    @Override
    public int compareTo(Employee other) {
        return Double.compare(this.salary, other.salary);
    }
}

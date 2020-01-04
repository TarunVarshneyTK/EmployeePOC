package com.sapient.employeePOC.pojo;

import java.io.Serializable;
import java.util.Objects;

public class Employee implements Serializable {
    private String empId;
    private String name;
    private String place;
    private float salary;

    public Employee() {
    }

    public Employee(String empId, String name, String place, float salary) {
        this.empId = empId;
        this.name = name;
        this.place = place;
        this.salary = salary;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId='" + empId + '\'' +
                ", name='" + name + '\'' +
                ", place='" + place + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return empId.equals(employee.empId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId);
    }
}

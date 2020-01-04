package com.sapient.employeePOC.service;

import com.sapient.employeePOC.pojo.Employee;

import java.util.ArrayList;
import java.util.List;

public interface EmployeeService {
    Employee getEmployee(String empId);

    List<Employee> updateEmpSalaryBasedOnPlace(String place, String percentage);

    ArrayList<Employee> getAllEmployee();
}

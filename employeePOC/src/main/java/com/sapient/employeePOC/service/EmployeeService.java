package com.sapient.employeePOC.service;

import com.sapient.employeePOC.Exception.NoRecordFoundException;
import com.sapient.employeePOC.pojo.Employee;

import java.util.ArrayList;
import java.util.List;

public interface EmployeeService {
    Employee getEmployee(String empId) throws NoRecordFoundException;

    List<Employee> updateEmpSalaryBasedOnPlace(String place, String percentage) throws NoRecordFoundException;

    ArrayList<Employee> getAllEmployee() throws NoRecordFoundException;
}

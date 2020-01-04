package com.sapient.employeePOC.dao;

import com.sapient.employeePOC.pojo.Employee;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface EmployeeCacheDao {
    List<Employee> getAllEmployee(InputStream inputReader);

    Employee saveEmployee(Employee employee);

    Map<String, Employee> getAllEmployees();

    void updateEmployeeList(Employee employee);

    Employee getEmployeeById(String empId);
}

package com.sapient.employeePOC.service;

import com.sapient.employeePOC.dao.EmployeeCacheDao;
import com.sapient.employeePOC.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    ResourceLoader resourceLoader;
    @Autowired
    EmployeeCacheDao employeeCacheDao;

    @PostConstruct
    public void init() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + "Employee.csv");
        BufferedReader br = null;
        employeeCacheDao.getAllEmployee(resource.getInputStream());
    }

    @Override
    public ArrayList<Employee> getAllEmployee() {
        return new ArrayList<Employee>(employeeCacheDao.getAllEmployees().values());
    }


    @Override
    public Employee getEmployee(String empId) {
        return employeeCacheDao.getEmployeeById(empId);
    }


    @Override
    public List<Employee> updateEmpSalaryBasedOnPlace(String place, String percentage) {
        ArrayList<Employee> employeeList = getAllEmployee();
        float perct = Float.valueOf(percentage) / 100;
        List<Employee> employeeSalaryUpdatedList = employeeList.stream().filter(e -> e.getPlace().equalsIgnoreCase(place)).map(e1 -> {
            e1.setSalary(e1.getSalary() + e1.getSalary() * perct);
            return e1;
        }).collect(Collectors.toList());
        employeeSalaryUpdatedList.stream().forEach(a -> employeeCacheDao.updateEmployeeList(a));
        return employeeSalaryUpdatedList;
    }


}

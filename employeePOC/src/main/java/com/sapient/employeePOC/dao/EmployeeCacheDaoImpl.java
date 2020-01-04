package com.sapient.employeePOC.dao;

import com.sapient.employeePOC.pojo.Employee;
import com.sapient.employeePOC.util.CSVutill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeCacheDaoImpl implements EmployeeCacheDao {

    @Autowired
    CSVutill csVutill;

    private HashOperations hashOperations;

    private RedisTemplate redisTemplate;

    public EmployeeCacheDaoImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    @Override
    public List<Employee> getAllEmployee(InputStream inputReader) {
        List<Employee> employeeList = csVutill.readEmployeeFromCSV(inputReader);
        employeeList.parallelStream().forEach(a -> saveEmployee(a));
        return employeeList;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        hashOperations.put("employee", employee.getEmpId(), employee);
        return employee;
    }

    @Override
    public Map<String, Employee> getAllEmployees() {
        return hashOperations.entries("employee");
    }

    @Override
    public void updateEmployeeList(Employee employee) {
        hashOperations.put("employee", employee.getEmpId(), employee);
    }

    @Override
    public Employee getEmployeeById(String empId) {
        return (Employee) hashOperations.get("employee", empId);
    }
}

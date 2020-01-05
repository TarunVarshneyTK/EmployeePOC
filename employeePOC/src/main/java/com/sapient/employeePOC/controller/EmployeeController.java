package com.sapient.employeePOC.controller;

import com.sapient.employeePOC.Exception.NoRecordFoundException;
import com.sapient.employeePOC.pojo.Employee;
import com.sapient.employeePOC.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/employee/place/{place}/salary/{percentage}", method = RequestMethod.PUT)
    public List<Employee> updateEmpSalary(@PathVariable String place, @PathVariable String percentage) throws NoRecordFoundException {
        return employeeService.updateEmpSalaryBasedOnPlace(place, percentage);
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public ArrayList<Employee> getEmployeeList() throws NoRecordFoundException {
        return employeeService.getAllEmployee();
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public Employee getEmployee(@PathVariable String id) throws NoRecordFoundException {
        return employeeService.getEmployee(id);
    }

}

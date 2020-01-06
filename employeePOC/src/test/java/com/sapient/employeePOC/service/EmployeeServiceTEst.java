package com.sapient.employeePOC.service;

import com.sapient.employeePOC.Exception.NoRecordFoundException;
import com.sapient.employeePOC.dao.EmployeeCacheDaoImpl;
import com.sapient.employeePOC.pojo.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {
    @Mock
    EmployeeCacheDaoImpl employeeCacheDao;

    @InjectMocks
    @Spy
    EmployeeServiceImpl employeeService;

    @BeforeEach
    void init() throws NoRecordFoundException {
        ArrayList<Employee> l = new ArrayList<>();
        l.add(new Employee("1", "Tarun", "MP", 50000));
        l.add(new Employee("1", "Anil", "Punjab", 40000));
        l.add(new Employee("1", "Raj", "Rajsthan", 35000));
        Mockito.doReturn(l).when(employeeService).getAllEmployee();
        Mockito.doNothing().when(employeeCacheDao).updateEmployeeList(any());
    }

    @Test
    void updateEmpSalaryBasedOnPlaceTest() throws NoRecordFoundException {
        ArrayList<Employee> expectedResult=new ArrayList<>();
        expectedResult.add(new Employee("1", "Tarun", "MP", 100000));
        Assertions.assertEquals(expectedResult,employeeService.updateEmpSalaryBasedOnPlace("MP","100"));
    }
}
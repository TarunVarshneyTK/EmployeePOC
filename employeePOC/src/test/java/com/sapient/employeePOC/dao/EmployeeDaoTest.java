package com.sapient.employeePOC.dao;

import com.sapient.employeePOC.Exception.NoRecordFoundException;
import com.sapient.employeePOC.pojo.Employee;
import com.sapient.employeePOC.util.CSVutill;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.InputStream;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class EmployeeDaoTest {

    @Mock
    CSVutill csVutill;
    @Mock
    InputStream inputStream;
    @Mock
    RedisTemplate<String, Employee> redisTemplate;
    @Mock
    HashOperations hashOperations;

    EmployeeCacheDaoImpl employeeCacheDao;

    @BeforeEach
    public void initEach() {
        when(redisTemplate.opsForHash()).thenReturn(hashOperations);
        doNothing().when(hashOperations).put(any(), any(), any());
        employeeCacheDao = new EmployeeCacheDaoImpl(redisTemplate);
        employeeCacheDao.csVutill = csVutill;
    }

    @Test
    void getAllEmployeeTest() throws NoRecordFoundException {
        ArrayList<Employee> l = new ArrayList<>();
        l.add(new Employee("1", "Tarun", "MP", 50000));
        l.add(new Employee("1", "Anil", "Punjab", 40000));
        l.add(new Employee("1", "Raj", "Rajsthan", 35000));

        when(csVutill.readEmployeeFromCSV(inputStream)).thenReturn(l);
        Assertions.assertEquals(l, employeeCacheDao.getAllEmployee(inputStream));
    }

    @Test
    void saveEmployeeTest() {
        Employee emp = new Employee("1", "Tarun", "MP", 50000);
        Assertions.assertEquals(emp, employeeCacheDao.saveEmployee(new Employee("1", "Tarun", "MP", 50000)));
    }
}

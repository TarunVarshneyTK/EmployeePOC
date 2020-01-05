package com.sapient.employeePOC.utill;

import com.sapient.employeePOC.pojo.Employee;
import com.sapient.employeePOC.util.CSVutill;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.ArrayList;

@ExtendWith(SpringExtension.class)

public class CSVutillTest {
    @Autowired
    ResourceLoader resourceLoader;

    @Test
    void readEmployeeFromCSVTest() throws IOException {
        ArrayList<Employee> l = new ArrayList<>();
        l.add(new Employee("1", "Tarun", "Bangalore", 5000));
        l.add(new Employee("2", "Arun", "UP", 4000));
        l.add(new Employee("3", "Pramod", "Bihar", 3000));
        Resource resource = resourceLoader.getResource("classpath:" + "Test.csv");
        CSVutill csVutill = new CSVutill();
        Assertions.assertEquals(l, csVutill.readEmployeeFromCSV(resource.getInputStream()));
    }

}

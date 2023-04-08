package com.sm.repo;


import com.sm.model.Employee;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EmployeeRepo {
    public static Map<String, Employee> map = new HashMap<>();

    public void add(Employee employee) {
        map.put(employee.getEmail().toLowerCase(), employee);
    }

    public Employee findByEmail(String email) {
        return map.get(email.toLowerCase());
    }
}

package com.sm.service;

import com.sm.config.RedisLock;
import com.sm.model.Employee;
import com.sm.repo.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    private final RedisLock redisLock;
    private final EmployeeRepo employeeRepo;

    public void addEmployee(Employee employee) {
        if (redisLock.tryLock()) {
            log.info("Lock");
            try {
                log.info("Employee name " + employee.getName());
                if (employee.getEmail() != null) {
                    Employee oldEmployee = employeeRepo.findByEmail(employee.getEmail());
                    if (oldEmployee == null) {
                        employeeRepo.add(employee);
                    } else {
                        log.error(oldEmployee.getEmail() + " is already present");
                    }
                }
            } finally {
                log.info("Lock Released");
                redisLock.unlock();
            }
        } else {
            log.error("lock is acquired by another thread");
        }
    }
}

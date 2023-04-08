package com.sm;


import com.sm.config.RedisConfig;
import com.sm.config.RedisLock;
import com.sm.model.Employee;
import com.sm.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
//@ImportAutoConfiguration(classes = {DataSourceAutoConfiguration.class, JacksonAutoConfiguration.class, RedisAutoConfiguration.class})
@Import({RedisConfig.class,RedisLock.class})
//@RequiredArgsConstructor
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService myService;

    @Test
    public void testConcurrentRequests() throws InterruptedException {
        List<Employee> list = new ArrayList<Employee>() {
            {
                add(new Employee("hello", "hello@hotmail.com"));
                add(new Employee("hello1", "hello1@hotmail.com"));
                add(new Employee("hello", "hello2@hotmail.com"));
                add(new Employee("hello2", "hello3@hotmail.com"));
            }
        };
        int numThreads = 4;
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads * 2);
        CountDownLatch countDownLatch = new CountDownLatch(numThreads);

        for (int i = 0; i < numThreads; i++) {
            executorService.execute(() -> {
                myService.addEmployee(list.get(ThreadLocalRandom.current().nextInt(4)));
                countDownLatch.countDown();
            });
        }

        countDownLatch.await(20, TimeUnit.SECONDS);
        assertEquals(0, countDownLatch.getCount());
    }


}


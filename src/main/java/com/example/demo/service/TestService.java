package com.example.demo.service;

import com.example.demo.entity.Test;
import com.example.demo.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    private TestRepository testRepository;
    private static long SLT_ID = 0;

    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public void addTest(String name, String input, String output) {
        Test test = new Test();
        test.setCourse_number("0");
        if (input.isEmpty())
            test.setInput("\r");
        test.setOutput(output);
        test.setTask_number(name);
        test.setID(String.valueOf(SLT_ID++));
        testRepository.save(test);
    }

    public Iterable<Test> getTestList() {
        return testRepository.findAll();
    }
}

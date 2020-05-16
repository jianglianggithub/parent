package com.server;


import com.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class TestService {

    @Autowired
    TestMapper testMapper;


    @Autowired
    TestService testService;

    @Transactional(propagation = Propagation.NESTED)
    public void test1(){
        testMapper.update();
        testService.test2();;
    }

    @Transactional
    public void test2(){
        int i=1/0;
        testMapper.update1();
    }


    public static void main(String[] args) {


    }
}

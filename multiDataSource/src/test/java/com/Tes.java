package com;


import com.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Tes {

    @Autowired
    TestService service;



    @Test
    public void aa() throws InterruptedException {
        service.a2();

    }

}

package com.ellen.demo.service;

import com.ellen.demo.domain.Result;
import com.ellen.demo.domain.Stuff;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StuffServiceTest {

    @Autowired
    private StuffService stuffService;

    @Test
    public void addOne() {
        Stuff stuff = new Stuff();
        stuff.setName("unittest");
        stuff.setQuantity(99);
        stuffService.addOne(stuff);

        Result result = stuffService.searchByName("unittest");
        assertNotNull(result.getData());
    }
}
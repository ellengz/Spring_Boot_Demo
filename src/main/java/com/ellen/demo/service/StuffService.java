package com.ellen.demo.service;

import com.ellen.demo.domain.Stuff;
import com.ellen.demo.repository.StuffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StuffService {

    @Autowired
    private StuffRepository stuffRepository;

    @Transactional
    public void addTranMultiple(){
        Stuff stuffA = new Stuff();
        stuffA.setName("thingA");
        stuffA.setQuantity(10);
        stuffRepository.save(stuffA);

        Stuff stuffB = new Stuff();
        stuffA.setName("thingB");
        stuffA.setQuantity(100);
        stuffRepository.save(stuffB);

    }

    public void addMultiple(){
        Stuff stuffC = new Stuff();
        stuffC.setName("thingC");
        stuffC.setQuantity(10);
        stuffRepository.save(stuffC);

        Stuff stuffD = new Stuff();
        stuffD.setName("thingD");
        stuffD.setQuantity(100);
        stuffRepository.save(stuffD);

    }
}

package com.ellen.demo.service;

import com.ellen.demo.domain.Result;
import com.ellen.demo.domain.Stuff;
import com.ellen.demo.enums.ResultEnum;
import com.ellen.demo.exception.StuffException;
import com.ellen.demo.repository.StuffRepository;
import com.ellen.demo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StuffService {

    @Autowired
    private StuffRepository stuffRepository;

    public Result addOne(Stuff stuff){

        if (stuff.getName().equals("")) {
            throw new StuffException(ResultEnum.ILLEGAL_NAME);
        }

        if (stuff.getQuantity() > 99) {
            throw new StuffException(ResultEnum.QUANTITY_TOO_BIG);
        }else if (stuff.getQuantity() < 0) {
            throw new StuffException(ResultEnum.QUANTITY_TOO_SMALL);
        }

        return ResultUtil.success(stuffRepository.save(stuff));
    }

    @Transactional
    public void addMultiple(){
        Stuff stuffA = new Stuff();
        stuffA.setName("thingA");
        stuffA.setQuantity(10);
        stuffRepository.save(stuffA);

        Stuff stuffB = new Stuff();
        stuffA.setName("thingB");
        stuffA.setQuantity(100);
        stuffRepository.save(stuffB);

    }
}

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

import java.util.ArrayList;
import java.util.List;

@Service
public class StuffService {

    @Autowired
    private StuffRepository stuffRepository;

    public Result addOne(Stuff stuff){

        // verify name
        if (stuff.getName().equals("")) {
            throw new StuffException(ResultEnum.ILLEGAL_NAME);
        }

        // verify quantity
        if (stuff.getQuantity() > 99) {
            throw new StuffException(ResultEnum.QUANTITY_TOO_BIG);
        }else if (stuff.getQuantity() < 0) {
            throw new StuffException(ResultEnum.QUANTITY_TOO_SMALL);
        }

        return ResultUtil.success(stuffRepository.save(stuff));
    }

    // use @Transactional to roll back when part of transaction failed
    @Transactional
    public List<Result> addMultiple(){
        List<Result> results = new ArrayList<>();
        for (int i=0; i<3; i++) {
            Stuff stuff = new Stuff();
            stuff.setName("test" + i);
            stuff.setQuantity(50*i);
            results.add(addOne(stuff));
        }
        return results;
    }
}

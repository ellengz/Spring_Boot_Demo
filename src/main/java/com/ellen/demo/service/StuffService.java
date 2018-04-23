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

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StuffService {

    @Autowired
    private StuffRepository stuffRepository;

    /**
     * find all
     * @return list of stuffs
     */
    public Result findAll() {
        return ResultUtil.success(stuffRepository.findAll());
    }

    /**
     * add a new stuff
     * @param stuff
     * @return Result to indicate error or success
     */
    public Result addOne(Stuff stuff){

        // alphabetical name
        if (!stuff.getName().matches("[a-zA-Z]+")) {
            throw new StuffException(ResultEnum.ILLEGAL_NAME);
        }

        // quantity in [0, 99]
        if (stuff.getQuantity() > 99) {
            throw new StuffException(ResultEnum.QUANTITY_TOO_BIG);
        }else if (stuff.getQuantity() < 0) {
            throw new StuffException(ResultEnum.QUANTITY_TOO_SMALL);
        }

        return ResultUtil.success(stuffRepository.save(stuff));
    }

    /**
     * search by Id
     * @param id
     * @return Result to indicate error or success
     */
    public Result searchById(Integer id) {
        if (stuffRepository.findById(id).isPresent()) {
            return ResultUtil.success(stuffRepository.findById(id).get());
        } else {
            throw new StuffException(ResultEnum.ID_NOT_EXIST);
        }
    }

    /**
     * search by Id
     * @param name
     * @return Result to indicate error or success
     */
    public Result searchByName(String name) {
        return ResultUtil.success(stuffRepository.findStuffsByName(name));
    }


    /**
     * use @Transactional to add multiple stuffs
     * roll back when part of transaction failed
     * @return Result to indicate error or success
     */
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

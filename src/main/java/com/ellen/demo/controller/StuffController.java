package com.ellen.demo.controller;

import com.ellen.demo.domain.Result;
import com.ellen.demo.domain.Stuff;
import com.ellen.demo.repository.StuffRepository;
import com.ellen.demo.service.StuffService;
import com.ellen.demo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class StuffController {

    @Autowired
    private StuffService stuffService;

    // search all
    @GetMapping(value = "/stuff")
    public Result stuffList(){
        return stuffService.findAll();
    }

    // add one
    @PostMapping(value = "/stuff")
    public Result stuffAdd(Stuff stuff){
       return stuffService.addOne(stuff);
    }

    // search by id
    @GetMapping(value = "/stuff/{id}")
    public Result stuffSearch(@PathVariable("id") Integer id){
        return stuffService.searchById(id);
    }

    // update by id
    @PutMapping(value = "/stuff/{id}")
    public Result stuffUpdate(@PathVariable("id") Integer id,
                             @RequestParam("name") String name,
                             @RequestParam("quantity") Integer quantity){

        Stuff stuff = new Stuff();
        stuff.setId(id);
        stuff.setName(name);
        stuff.setQuantity(quantity);
        return stuffService.updateById(stuff);
    }

    // delete by id
    @DeleteMapping(value = "/stuff/{id}")
    public Result stuffDelete(@PathVariable("id") Integer id){
        return stuffService.deleteById(id);
    }

    // search by name
    @GetMapping(value = "/stuff/name/{name}")
    public Result stuffSearchByName(@PathVariable("name") String name){
        return stuffService.searchByName(name);
    }

    // @Transactional test case
    // add three stuffs (3rd is illegal)
    // no records inserted, however id still increased
    @PostMapping(value = "stuff/addMul")
    public List<Result> addMul(){
        return stuffService.addMultiple();
    }
}

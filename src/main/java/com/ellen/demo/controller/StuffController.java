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
    private StuffRepository stuffRepository;

    @Autowired
    private StuffService stuffService;

    // search all
    @GetMapping(value = "/stuff")
    public List<Stuff> stuffList(){
        return stuffRepository.findAll();
    }

    // add one
    @PostMapping(value = "/stuff")
    public Result<Stuff> stuffAdd(@Valid Stuff stuff, BindingResult bindingResult){
        Result result = new Result();
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(1, bindingResult.getFieldError().getDefaultMessage());
        }
       return ResultUtil.success(stuffRepository.save(stuff));
    }

    // search by id
    @GetMapping(value = "/stuff/{id}")
    public Stuff stuffSearch(@PathVariable("id") Integer id){
        return stuffRepository.findById(id).get();
    }

    // update by id
    @PutMapping(value = "/stuff/{id}")
    public Stuff stuffUpdate(@PathVariable("id") Integer id,
                             @RequestParam("name") String name,
                             @RequestParam("quantity") Integer quantity){

        Stuff stuff = new Stuff();
        stuff.setId(id);
        stuff.setName(name);
        stuff.setQuantity(quantity);

        return stuffRepository.save(stuff);
    }

    // delete by id
    @DeleteMapping(value = "/stuff/{id}")
    public void stuffDelete(@PathVariable("id") Integer id){
        stuffRepository.deleteById(id);
    }

    // search by name
    @GetMapping(value = "/stuff/name/{name}")
    public List<Stuff> stuffSearchByName(@PathVariable("name") String name){
        return stuffRepository.findStuffsByName(name);
    }

    // add two stuffs (one illegal)
    // no records inserted, however id still increased
    @PostMapping(value = "stuff/addMul")
    public void addMul(){
        stuffService.addMultiple();
    }
}

package com.ellen.demo.controller;

import com.ellen.demo.domain.Stuff;
import com.ellen.demo.repository.StuffRepository;
import com.ellen.demo.service.StuffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Stuff stuffAdd(@RequestParam("name") String name, @RequestParam("quantity") Integer quantity){

        Stuff stuff = new Stuff();
        stuff.setName(name);
        stuff.setQuantity(quantity);

        return stuffRepository.save(stuff);
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
    // result: legal record will be inserted
    @PostMapping(value = "stuff/addMul")
    public void addMul(){
        stuffService.addMultiple();
    }

    // add two stuffs (one illegal) with @Transactional
    // result: no records inserted, however id still increased
    @PostMapping(value = "stuff/addTranMul")
    public void addTranMul(){
        stuffService.addTranMultiple();
    }
}
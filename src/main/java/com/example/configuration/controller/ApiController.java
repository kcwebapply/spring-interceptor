package com.example.configuration.controller;


import com.example.configuration.model.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @Autowired
    SampleRepository sampleRepository;

    @RequestMapping(value = "/test/{id}",method = RequestMethod.GET)
    public String test(@PathVariable String id){

        return sampleRepository.getStatus();
    }

    @RequestMapping(value = "/proper",method = RequestMethod.GET)
    public int proper(){
        return 2;
    }

    @RequestMapping(value = "/adminview",method = RequestMethod.GET)
    public int admin(){
        return 2;
    }

    public String echo(){
        return "echo\n";
    }
}

package com.example.configuration.model;


import org.springframework.stereotype.Component;

@Component
public class SampleRepository {

    public String getStatus(){
        return "200";
    }
}

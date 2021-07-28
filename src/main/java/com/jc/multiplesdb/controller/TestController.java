package com.jc.multiplesdb.controller;

import com.jc.multiplesdb.models.Resources;
import com.jc.multiplesdb.services.QueryServices;
import com.jc.multiplesdb.utils.EntityManagerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/databases")
public class TestController {

    @Autowired
    private QueryServices services;


    @GetMapping("/all/{db}")
    public List<Resources> findAll(@PathVariable String db){
        return services.findAll(db);
    }

}

package com.example.SafetyAlerts.controllers;


import com.example.SafetyAlerts.dao.GetFlood;
import com.example.SafetyAlerts.dao.GetPersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/flood")
public class RestControllerFlood {

    @Autowired
    private GetFlood getFlood;

    @GetMapping
    // @ResponseBody
    public ArrayList<String> getFlood(String station)  {

        return getFlood.getFlood(station);
    }



}

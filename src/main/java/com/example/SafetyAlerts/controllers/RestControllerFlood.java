package com.example.SafetyAlerts.controllers;


import com.example.SafetyAlerts.dao.GetFlood;
import com.example.SafetyAlerts.modeles.FloodList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flood")
public class RestControllerFlood {

    @Autowired
    private GetFlood getFlood;

    @GetMapping
    // @ResponseBody
    public FloodList getFlood(String station)  {

        return getFlood.getFlood(station);
    }



}

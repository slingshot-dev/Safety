package com.example.SafetyAlerts.controllers;


import com.example.SafetyAlerts.dao.GetFire;
import com.example.SafetyAlerts.dao.GetFirestation;
import com.example.SafetyAlerts.modeles.FirestationList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/firestation")
public class RestControllerFirestation {

    @Autowired
    private GetFirestation getFirestation;

    @GetMapping
    // @ResponseBody
    public FirestationList getFirestation(@RequestParam("stationNumber") String station)  {

        return getFirestation.getFirestation(station);
    }


}

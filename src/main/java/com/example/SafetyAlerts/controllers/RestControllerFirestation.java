package com.example.SafetyAlerts.controllers;


import com.example.SafetyAlerts.modeles.FirestationUrl;
import com.example.SafetyAlerts.services.GetFirestation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/firestation")
public class RestControllerFirestation {

    GetFirestation getFirestation = new GetFirestation();

    @GetMapping
    // @ResponseBody
    public FirestationUrl getFirestation(@RequestParam("stationNumber") String station)  {

        return getFirestation.getFirestation(station);
    }


}

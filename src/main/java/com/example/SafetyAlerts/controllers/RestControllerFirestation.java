package com.example.SafetyAlerts.controllers;


import com.example.SafetyAlerts.dao.IGetFirestation;
import com.example.SafetyAlerts.modeles.FirestationUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/firestation")
public class RestControllerFirestation {

    private final IGetFirestation igetFirestation;
    public RestControllerFirestation(IGetFirestation iGetFirestation) {
        this.igetFirestation = iGetFirestation;
    }


    @GetMapping
    // @ResponseBody
    public FirestationUrl getFirestation(@RequestParam("stationNumber") String station)  {

        return igetFirestation.getFirestation(station);
    }


}

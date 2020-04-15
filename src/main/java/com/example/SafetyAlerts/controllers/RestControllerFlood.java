package com.example.SafetyAlerts.controllers;


import com.example.SafetyAlerts.dao.IGetFlood;
import com.example.SafetyAlerts.modeles.FloodList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flood")
public class RestControllerFlood {


    private final IGetFlood igetFlood;
    public RestControllerFlood(IGetFlood igetFlood) {
        this.igetFlood = igetFlood;
    }


    @GetMapping
    public FloodList getFlood(String station)  {

        return igetFlood.getFlood(station);
    }



}

package com.example.SafetyAlerts.controllers;


import com.example.SafetyAlerts.modeles.FloodUrl;
import com.example.SafetyAlerts.services.GetFlood;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flood")
public class RestControllerFlood {


GetFlood getFlood = new GetFlood();


    @GetMapping
    public List<FloodUrl> getFlood(String station)  {

        return getFlood.getFlood(station);
    }

}

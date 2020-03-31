package com.example.SafetyAlerts.controllers;


import com.example.SafetyAlerts.dao.GetFire;
import com.example.SafetyAlerts.dao.GetPhoneAlert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/phoneAlert")
public class RestControllerPhoneAlert {

    @Autowired
    private GetPhoneAlert getPhoneAlert;

    @GetMapping
    // @ResponseBody
    public ArrayList<String> getPhoneAlert(String station)  {

        return getPhoneAlert.getPhoneAlert(station);
    }
}

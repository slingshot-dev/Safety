package com.example.SafetyAlerts.controllers;


import com.example.SafetyAlerts.dao.GetChildAlert;
import com.example.SafetyAlerts.dao.GetFire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/childAlert")
public class RestControllerChildAlert {

    @Autowired
    private GetChildAlert getChildAlert;

    @GetMapping
    // @ResponseBody
    public ArrayList<String> getChildAlert(String address)  {

        return getChildAlert.getChildAlert(address);
    }

}

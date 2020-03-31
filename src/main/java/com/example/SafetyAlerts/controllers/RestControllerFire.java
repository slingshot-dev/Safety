package com.example.SafetyAlerts.controllers;

import com.example.SafetyAlerts.dao.GetFire;
import com.example.SafetyAlerts.dao.GetFlood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/fire")
public class RestControllerFire {

    @Autowired
    private GetFire getFire;

    @GetMapping
    // @ResponseBody
    public ArrayList<String> getFlood(String address)  {

        return getFire.getFire(address);
    }


}

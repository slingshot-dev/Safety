package com.example.SafetyAlerts.controllers;

import com.example.SafetyAlerts.modeles.ChildAlertUrl;
import com.example.SafetyAlerts.services.GetChildAlert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/childAlert")
public class RestControllerChildAlert {


    GetChildAlert getChildAlert = new GetChildAlert();

    @GetMapping
    public ArrayList<ChildAlertUrl> getChildAlert(String address)  {

        return getChildAlert.getChildAlert(address);
    }

}

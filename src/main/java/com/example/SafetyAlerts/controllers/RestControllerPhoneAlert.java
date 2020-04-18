package com.example.SafetyAlerts.controllers;


import com.example.SafetyAlerts.modeles.PhoneAlertUrl;
import com.example.SafetyAlerts.services.GetPhoneAlert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/phoneAlert")
public class RestControllerPhoneAlert {


    GetPhoneAlert getPhoneAlert = new GetPhoneAlert();

    @GetMapping
    public List<PhoneAlertUrl> getPhoneAlert(String station)  {

        return getPhoneAlert.getPhoneAlert(station);
    }
}

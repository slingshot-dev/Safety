package com.example.SafetyAlerts.controllers;


import com.example.SafetyAlerts.dao.IGetPhoneAlert;
import com.example.SafetyAlerts.modeles.PhoneAlertList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/phoneAlert")
public class RestControllerPhoneAlert {


    private final IGetPhoneAlert igetPhoneAlert;
    public RestControllerPhoneAlert(IGetPhoneAlert igetPhoneAlert) {
        this.igetPhoneAlert = igetPhoneAlert;
    }

    @GetMapping
    // @ResponseBody
    public PhoneAlertList getPhoneAlert(String station)  {

        return igetPhoneAlert.getPhoneAlert(station);
    }
}

package com.example.SafetyAlerts.controllers;

import com.example.SafetyAlerts.dao.IGetFire;
import com.example.SafetyAlerts.modeles.FireList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fire")
public class RestControllerFire {

    private final IGetFire igetFire;
    public RestControllerFire(IGetFire igetFire) {
        this.igetFire = igetFire;
    }


    @GetMapping
    // @ResponseBody
    public FireList getFlood(String address)  {

        return igetFire.getFire(address);
    }


}

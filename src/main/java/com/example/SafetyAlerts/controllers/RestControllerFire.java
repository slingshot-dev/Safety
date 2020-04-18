package com.example.SafetyAlerts.controllers;

import com.example.SafetyAlerts.dao.impl.GetFire;
import com.example.SafetyAlerts.modeles.FireUrl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fire")
public class RestControllerFire {

    GetFire getFire = new GetFire();

    @GetMapping
    public List<FireUrl> getFire(String address)  {

        return getFire.getFire(address);
    }


}

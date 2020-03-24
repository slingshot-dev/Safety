package com.example.SafetyAlerts.controllers;


import com.example.SafetyAlerts.dao.GetPersonInfo;
import com.example.SafetyAlerts.modeles.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/personinfo")
public class RestControllerPersonsInfo {

    @Autowired
    private GetPersonInfo getPersonInfo;

    @GetMapping
    @ResponseBody
    public List<Person> getPersonInfo() {

        return getPersonInfo.getPersonInfo();
    }

}


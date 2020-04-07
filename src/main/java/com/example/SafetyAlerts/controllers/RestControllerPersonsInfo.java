package com.example.SafetyAlerts.controllers;


import com.example.SafetyAlerts.dao.GetPersonInfo;
import com.example.SafetyAlerts.modeles.PersonList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/personinfo")
public class RestControllerPersonsInfo {

    @Autowired
    private GetPersonInfo getPersonInfo;

    @GetMapping
    // @ResponseBody
    public PersonList getPersonx(String firstname, String lastname) {

        return getPersonInfo.getPersonFindByName(firstname, lastname);
    }

}


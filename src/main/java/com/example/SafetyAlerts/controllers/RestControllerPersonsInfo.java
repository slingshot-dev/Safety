package com.example.SafetyAlerts.controllers;


import com.example.SafetyAlerts.modeles.PersonUrl;
import com.example.SafetyAlerts.services.GetPersonInfo;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/personinfo")
public class RestControllerPersonsInfo {


    GetPersonInfo getPersonInfo = new GetPersonInfo();

    @GetMapping
    public List<PersonUrl> getPerson(String firstname, String lastname) {

        return getPersonInfo.getPersonFindByName(firstname, lastname);
    }



}


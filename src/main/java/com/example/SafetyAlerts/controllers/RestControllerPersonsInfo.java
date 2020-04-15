package com.example.SafetyAlerts.controllers;


import com.example.SafetyAlerts.dao.IGetPersonInfo;
import com.example.SafetyAlerts.modeles.PersonList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/personinfo")
public class RestControllerPersonsInfo {


    private final IGetPersonInfo igetPersonInfo;
    public RestControllerPersonsInfo(IGetPersonInfo igetPersonInfo) {
        this.igetPersonInfo = igetPersonInfo;
    }

    @GetMapping
    // @ResponseBody
    public PersonList getPersonx(String firstname, String lastname) {

        return igetPersonInfo.getPersonFindByName(firstname, lastname);
    }

}


package com.example.SafetyAlerts.controllers;


import com.example.SafetyAlerts.modeles.PersonUrl;
import com.example.SafetyAlerts.services.GetPersonInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/personinfo")
public class RestControllerPersonsInfo {
    private static final Logger logger = LogManager.getLogger(RestControllerPersonEmail.class);
    private final GetPersonInfo getPersonInfo;

    public RestControllerPersonsInfo(GetPersonInfo getPersonInfo) {
        this.getPersonInfo = getPersonInfo;
    }


    @GetMapping
    public List<PersonUrl> getPerson(String firstname, String lastname) throws Exception {

        if (firstname.isEmpty() || lastname.isEmpty()) {
            logger.error("Parameter Lastname or Firstname is missing");
            throw new Exception("Parameters : Lastname and Firstname values, are necessary");
        } else {
            logger.info("Get Persons OK");
            return getPersonInfo.getPersonFindByName(firstname, lastname);
        }
    }
}


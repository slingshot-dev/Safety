package com.example.SafetyAlerts.controllers;


import com.example.SafetyAlerts.modeles.PersonUrl;
import com.example.SafetyAlerts.services.PersonInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/personinfo")
public class RestControllerPersonsInfo {
    private static final Logger logger = LogManager.getLogger(RestControllerPersonsInfo.class);
    private final PersonInfoService personInfoService;

    public RestControllerPersonsInfo(PersonInfoService personInfoService) {
        this.personInfoService = personInfoService;
    }


    @GetMapping
    public List<PersonUrl> getPerson(String firstname, String lastname) throws Exception {

        if (firstname.isEmpty() || lastname.isEmpty()) {
            logger.error("Parameter Lastname or Firstname is missing");
            throw new Exception("Parameters : Lastname and Firstname values, are necessary");
        } else {
            logger.info("Get Persons OK");
            return personInfoService.getPersonFindByName(firstname, lastname);
        }
    }
}


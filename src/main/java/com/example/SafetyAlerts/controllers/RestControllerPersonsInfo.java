package com.example.SafetyAlerts.controllers;


import com.example.SafetyAlerts.modeles.PersonInfos;
import com.example.SafetyAlerts.services.PersonInfoService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.converter.json.MappingJacksonValue;
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
    public Object getPerson(String firstname, String lastname) throws Exception {

        if (firstname.isEmpty() || lastname.isEmpty()) {
            logger.error("Parameter Lastname or Firstname is missing");
            throw new Exception("Parameters : Lastname and Firstname values, are necessary");
        } else {
            logger.info("Get Persons OK");
            List<PersonInfos> resultPersonInfos = personInfoService.getPersonFindByName(firstname, lastname);

            MappingJacksonValue result = new MappingJacksonValue(resultPersonInfos);
            FilterProvider filter = new SimpleFilterProvider().addFilter("personFilter", SimpleBeanPropertyFilter.filterOutAllExcept("firstName", "lastName", "email", "age", "medics", "allergies"));
            result.setFilters(filter);

            return result;
        }
    }
}


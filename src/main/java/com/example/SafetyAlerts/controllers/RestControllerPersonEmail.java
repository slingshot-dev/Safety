package com.example.SafetyAlerts.controllers;

import com.example.SafetyAlerts.modeles.PersonInfos;
import com.example.SafetyAlerts.services.EmailInfoService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/communityEmail")
public class RestControllerPersonEmail {

    private static final Logger logger = LogManager.getLogger(RestControllerPersonEmail.class);

    private final EmailInfoService emailInfoService;

    public RestControllerPersonEmail(EmailInfoService emailInfoService) {
        this.emailInfoService = emailInfoService;
    }

    /**
     *
     * @param city : Parametre du nom de Ville
     * @return : Retourne les informations du Service EmailInfoService
     * @throws Exception : Exception si parametres non renseignés
     */

    @GetMapping
    public Object getEmail(@RequestParam("city") String city) throws Exception {
        if (city.isEmpty()) {
            logger.error("Parameter City is missing");
            throw new Exception("Parameter : city value, is necessary");
        } else {
            logger.info("Get Emails OK");
            List<PersonInfos> resultPersonInfos = emailInfoService.getEmail(city);

            MappingJacksonValue result = new MappingJacksonValue(resultPersonInfos);
            FilterProvider filter = new SimpleFilterProvider().addFilter("personFilter", SimpleBeanPropertyFilter.filterOutAllExcept("firstName", "lastName", "email"));
            result.setFilters(filter);

            return result;
        }
    }
}

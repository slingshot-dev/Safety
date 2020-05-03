package com.example.SafetyAlerts.controllers;


import com.example.SafetyAlerts.modeles.PersonInfos;
import com.example.SafetyAlerts.services.PhoneAlertService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** Controleur de l'URL PhoneAlert :
 * Cette url retourne une liste des numéros de téléphone des résidents desservis par la caserne de
 * pompiers. Nous l'utilisons pour envoyer des messages texte d'urgence à des foyers spécifiques.
 */

@RestController
@RequestMapping("/phoneAlert")
public class RestControllerPhoneAlert {
    private static final Logger logger = LogManager.getLogger(RestControllerPhoneAlert.class);
    private final PhoneAlertService phoneAlertService;

    public RestControllerPhoneAlert(PhoneAlertService phoneAlertService) {
        this.phoneAlertService = phoneAlertService;
    }

    /**
     *
     * @param station : Numero de Firestation
     * @return : Retourn les informations du Service PhoneAlertService
     * @throws Exception : Exception si parametres non renseignés
     */

    @GetMapping
    public Object getPhoneAlert(String station) throws Exception {

        if (station.isEmpty()) {
            logger.error("Parameter Station is missing");
            throw new Exception("Parameter : Station value, is necessary");
        } else {
            logger.info("Get Phones OK");
            List<PersonInfos> resultPersonInfos = phoneAlertService.getPhoneAlert(station);

            MappingJacksonValue result = new MappingJacksonValue(resultPersonInfos);
            FilterProvider filter = new SimpleFilterProvider().addFilter("personFilter", SimpleBeanPropertyFilter.filterOutAllExcept("firstName", "lastName", "phone"));
            result.setFilters(filter);

            return result;
        }
    }
}

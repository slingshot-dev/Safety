package com.example.SafetyAlerts.controllers;


import com.example.SafetyAlerts.modeles.FirestationUrl;
import com.example.SafetyAlerts.services.GetFirestation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/firestation")
public class RestControllerFirestation {
    private static final Logger logger = LogManager.getLogger(RestControllerPersonEmail.class);

    private final GetFirestation getFirestation;
    public RestControllerFirestation(GetFirestation getFirestation) {
        this.getFirestation = getFirestation;
    }


    @GetMapping
    public FirestationUrl getFirestation(@RequestParam("stationNumber") String station) throws Exception {

        if (station.isEmpty()) {
            logger.error("Parameter Station is missing");
            throw new Exception("Parameter : Station value, is necessary");
        } else {
            logger.info("Get Station OK");
            return getFirestation.getFirestation(station);
        }
    }
}

package com.example.SafetyAlerts.controllers;


import com.example.SafetyAlerts.modeles.FloodUrl;
import com.example.SafetyAlerts.services.FloodService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flood")
public class RestControllerFlood {
    private static final Logger logger = LogManager.getLogger(RestControllerFlood.class);
    private final FloodService floodService;

    public RestControllerFlood(FloodService floodService) {
        this.floodService = floodService;
    }


    @GetMapping
    public List<FloodUrl> getFlood(String station) throws Exception {

        if (station.isEmpty()) {
            logger.error("Parameter Station is missing");
            throw new Exception("Parameter : Station value, is necessary");
        } else {
            logger.info("Get Persons from Station OK");
            return floodService.getFlood(station);
        }
    }
}

package com.example.SafetyAlerts.controllers;

import com.example.SafetyAlerts.modeles.FireUrl;
import com.example.SafetyAlerts.services.GetFire;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fire")
public class RestControllerFire {
    private static final Logger logger = LogManager.getLogger(RestControllerPersonEmail.class);

    public final GetFire getFire;

    public RestControllerFire(GetFire getFire) {
        this.getFire = getFire;
    }


    @GetMapping
    public List<FireUrl> getFire(String address) throws Exception {

        if (address.isEmpty()) {
            logger.error("Parameter Address is missing");
            throw new Exception("Parameter : Address value, is necessary");
        } else {
            logger.info("Get Address OK");
            return getFire.getFire(address);
        }
    }
}

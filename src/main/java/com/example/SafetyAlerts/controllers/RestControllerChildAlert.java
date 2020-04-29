package com.example.SafetyAlerts.controllers;

import com.example.SafetyAlerts.modeles.ChildAlertUrl;
import com.example.SafetyAlerts.services.ChildAlertService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/childAlert")
public class RestControllerChildAlert {

    private static final Logger logger = LogManager.getLogger(RestControllerPersonEmail.class);
/*    private final GetChildAlert getChildAlert;*/
private final ChildAlertService childAlertService;

    public RestControllerChildAlert(ChildAlertService childAlertService) {
        this.childAlertService = childAlertService;
    }


    /**
     *
     * @param address
     * @return
     * @throws Exception
     */

    @GetMapping
    public ArrayList<ChildAlertUrl> getChildAlert(String address) throws Exception {

        if (address.isEmpty()) {
            logger.error("Parameter Address is missing");
            throw new Exception("Parameter : Address value, is necessary");
        } else {
            logger.info("Get Childs from address OK");
            return childAlertService.getChildAlert(address);
        }
    }
}

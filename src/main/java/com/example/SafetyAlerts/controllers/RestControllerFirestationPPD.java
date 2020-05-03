package com.example.SafetyAlerts.controllers;

import com.example.SafetyAlerts.modeles.Firestation;
import com.example.SafetyAlerts.services.FirestationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

/**
 * Controleur de modification de la liste firestation
 */

@RestController
@RequestMapping("/firestation")
public class RestControllerFirestationPPD {
    private static final Logger logger = LogManager.getLogger(RestControllerFirestationPPD.class);
    private final FirestationService setFirestation;

    public RestControllerFirestationPPD(FirestationService setFirestation) {
        this.setFirestation = setFirestation;
    }


    @PostMapping("/post")
    public void addFirestation(Firestation addFirestation) throws Exception {

        if (addFirestation.getAddress().isEmpty() || addFirestation.getStation().isEmpty()) {
            logger.error("Parameters Address & Station are missing");
            throw new Exception("Parameters : Address & Station values, are necessary");
        } else {
            logger.info("Add Firestation OK");
            setFirestation.setAddFirestation(addFirestation);
        }
    }

    @PutMapping("/put")
    public void updatePerson(Firestation putFirestation) throws Exception {
        if (putFirestation.getAddress().isEmpty() || putFirestation.getStation().isEmpty()) {
            logger.error("Parameters Address & Station are missing");
            throw new Exception("Parameters : Address & Station values, are necessary");
        } else {
            logger.info("Update Firestation OK");
            setFirestation.setUpdateFirestation(putFirestation);
        }
    }

    @DeleteMapping("/delete")
    public void removePerson(Firestation removeFirestation) throws Exception {
        if (removeFirestation.getAddress().isEmpty() || removeFirestation.getStation().isEmpty()) {
            logger.error("Parameters Address & Station are missing");
            throw new Exception("Parameters : Address & Station values, are necessary");
        } else {
            logger.info("Delete Firestation OK");
            setFirestation.setRemoveFirestation(removeFirestation);
        }
    }
}


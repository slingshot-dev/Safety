package com.example.SafetyAlerts.controllers;

import com.example.SafetyAlerts.modeles.ChildAlertUrl;
import com.example.SafetyAlerts.services.ChildAlertService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Controleur de la liste ChildAlert.
 * Cette url retourne une liste d'enfants (tout individu âgé de 18 ans ou moins) habitant à cette adresse.
 * La liste comprend le prénom et le nom de famille de chaque enfant, son âge et une liste des autres
 * membres du foyer. S'il n'y a pas d'enfant, cette url renvoie une chaîne vide.
 */

@RestController
@RequestMapping("/childAlert")
public class RestControllerChildAlert {

    private static final Logger logger = LogManager.getLogger(RestControllerChildAlert.class);
    private final ChildAlertService childAlertService;

    public RestControllerChildAlert(ChildAlertService childAlertService) {
        this.childAlertService = childAlertService;
    }


    /**
     *
     * @param address : Adresse de la ou les personnes a remonter
     * @return : Retourne les informations au client, issues du service childAlertService
     * @throws Exception : Exception si parametres non renseignés
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

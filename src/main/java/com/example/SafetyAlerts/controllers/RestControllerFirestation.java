package com.example.SafetyAlerts.controllers;


import com.example.SafetyAlerts.modeles.FirestationUrl;
import com.example.SafetyAlerts.services.FirestationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Controleur de l'URL firestation.
 * Cette url retourne une liste des personnes couvertes par la caserne de pompiers correspondante.
 La liste inclue les informations spécifiques suivantes : prénom, nom, adresse, numéro de téléphone. De plus,
 elle fournie un décompte du nombre d'adultes et du nombre d'enfants (tout individu âgé de 18 ans ou
 moins) dans la zone desservie.
 *
 */

@RestController
@RequestMapping("/firestation")
public class RestControllerFirestation {
    private static final Logger logger = LogManager.getLogger(RestControllerFirestation.class);
    private final FirestationService firestationService;

    public RestControllerFirestation(FirestationService firestationService) {
        this.firestationService = firestationService;
    }

    /**
     *
     * @param station : Numero de la Station
     * @return : Retourne les information du service FirestationService
     * @throws Exception : Exception si parametres non renseignés
     */

    @GetMapping
    public FirestationUrl getFirestation(@RequestParam("stationNumber") String station) throws Exception {

        if (station.isEmpty()) {
            logger.error("Parameter Station is missing");
            throw new Exception("Parameter : Station value, is necessary");
        } else {
            logger.info("Get Station OK");
            return firestationService.getFirestation(station);
        }
    }
}

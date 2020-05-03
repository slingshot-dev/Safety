package com.example.SafetyAlerts.controllers;

import com.example.SafetyAlerts.modeles.Person;
import com.example.SafetyAlerts.services.PersonInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

/**
 * Controleur de modification de la liste de personne :
 */

@RestController
@RequestMapping("/person")
public class RestControllerPersonPPD {
    private static final Logger logger = LogManager.getLogger(RestControllerPersonPPD.class);
    private final PersonInfoService setPerson;

    public RestControllerPersonPPD(PersonInfoService setPerson) {
        this.setPerson = setPerson;
    }


    @PostMapping("/post")
    public void addPerson(Person person) throws Exception {
        if (person.getFirstName().isEmpty() || person.getLastName().isEmpty() || person.getAddress().isEmpty() || person.getCity().isEmpty() || person.getPhone().isEmpty() || person.getEmail().isEmpty()) {
            logger.error("One or more Parameters Firstname, Lastname, Address, City, Zip, Phone & Email are missing");
            throw new Exception("Parameters : FFirstname, Lastname, Address, City, Zip, Phone & Email, are necessary");
        } else {
            logger.info("Add Person OK");
            setPerson.setAddPerson(person);
        }
    }

    @PutMapping("/put")
    public void updatePerson(Person putPerson) throws Exception {
        if (putPerson.getFirstName().isEmpty() || putPerson.getLastName().isEmpty() || putPerson.getAddress().isEmpty() || putPerson.getCity().isEmpty() || putPerson.getPhone().isEmpty() || putPerson.getEmail().isEmpty()) {
            logger.error("One or more Parameters Firstname, Lastname, Address, City, Zip, Phone & Email are missing");
            throw new Exception("Parameters : FFirstname, Lastname, Address, City, Zip, Phone & Email, are necessary");
        } else {
            logger.info("Update Person OK");
            setPerson.setUpdatePerson(putPerson);
        }
    }

    @DeleteMapping("/delete")
    public void removePerson(Person removePerson) throws Exception {
        if (removePerson.getFirstName().isEmpty() || removePerson.getLastName().isEmpty()) {
            logger.error("One or more Parameters Firstname, Lastname are missing");
            throw new Exception("Parameters : Firstname, Lastname are necessary");
        } else {
            logger.info("Delete Person OK");
            setPerson.setRemovePerson(removePerson);
        }
    }
}

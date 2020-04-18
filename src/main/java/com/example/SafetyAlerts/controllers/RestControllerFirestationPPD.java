package com.example.SafetyAlerts.controllers;

import com.example.SafetyAlerts.modeles.Firestation;
import com.example.SafetyAlerts.services.SetFirestation;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/firestation")
public class RestControllerFirestationPPD {


SetFirestation setFirestation = new SetFirestation();

    @PostMapping("/post")
    public void addFirestation(Firestation addFirestation) {

        setFirestation.setAddFirestation(addFirestation);
    }


    @PutMapping("/put")
    public void updatePerson(Firestation putFirestation){
        setFirestation.setUpdateFirestation(putFirestation);
    }

    @DeleteMapping("/delete")
    public void removePerson(Firestation removeFirestation){
        setFirestation.setRemoveFirestation(removeFirestation);
    }


}


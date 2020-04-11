package com.example.SafetyAlerts.controllers;

import com.example.SafetyAlerts.dao.SetNewFirestation;
import com.example.SafetyAlerts.dao.SetNewPerson;
import com.example.SafetyAlerts.modeles.Firestation;
import com.example.SafetyAlerts.modeles.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/firestation")
public class RestControllerFirestationPPD {


        @Autowired
        SetNewFirestation setNewFirestation;

        @PostMapping("/post")
        public void addFirestation(Firestation addFirestation) {
            setNewFirestation.setAddFirestation(addFirestation);
        }

        @PutMapping("/put")
        public void updatePerson(Firestation putFirestation){
            setNewFirestation.setUpdateFirestation(putFirestation);
        }

        @DeleteMapping("/delete")
        public void removePerson(Firestation removeFirestation){
            setNewFirestation.setRemoveFirestation(removeFirestation);
        }


/*    public @ResponseBody

    ResponseEntity<String> post() {


        return new ResponseEntity<String>("POST Response", HttpStatus.OK);
    }*/

}

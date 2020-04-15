package com.example.SafetyAlerts.controllers;

import com.example.SafetyAlerts.dao.ISetNewFirestation;
import com.example.SafetyAlerts.modeles.Firestation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/firestation")
public class RestControllerFirestationPPD {



    private final ISetNewFirestation isetNewFirestation;
    public RestControllerFirestationPPD(ISetNewFirestation isetNewFirestation) {
        this.isetNewFirestation = isetNewFirestation;
    }


        @PostMapping("/post")
        public void addFirestation(Firestation addFirestation) {
            isetNewFirestation.setAddFirestation(addFirestation);
        }

        @PutMapping("/put")
        public void updatePerson(Firestation putFirestation){
            isetNewFirestation.setUpdateFirestation(putFirestation);
        }

        @DeleteMapping("/delete")
        public void removePerson(Firestation removeFirestation){
            isetNewFirestation.setRemoveFirestation(removeFirestation);
        }


/*    public @ResponseBody

    ResponseEntity<String> post() {


        return new ResponseEntity<String>("POST Response", HttpStatus.OK);
    }*/

}

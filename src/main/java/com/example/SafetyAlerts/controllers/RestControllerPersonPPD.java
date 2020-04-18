package com.example.SafetyAlerts.controllers;

import com.example.SafetyAlerts.modeles.Person;
import com.example.SafetyAlerts.services.SetPerson;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/person")
public class RestControllerPersonPPD {


SetPerson setPerson = new SetPerson();

    @PostMapping("/post")
    public void addPerson(Person person) {
        setPerson.setAddPerson(person);
    }

    @PutMapping("/put")
    public void updatePerson(Person putPerson){
        setPerson.setUpdatePerson(putPerson);
    }

    @DeleteMapping("/delete")
    public void removePerson(Person removePerson){
        setPerson.setRemovePerson(removePerson);
    }


}

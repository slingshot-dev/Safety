package com.example.SafetyAlerts.controllers;

import com.example.SafetyAlerts.dao.ISetNewPerson;
import com.example.SafetyAlerts.modeles.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/person")
public class RestControllerPersonPPD {


    private final ISetNewPerson isetNewPerson;
    public RestControllerPersonPPD(ISetNewPerson isetNewPerson) {
        this.isetNewPerson = isetNewPerson;
    }

    @PostMapping("/post")
    public void addPerson(Person person) {
        isetNewPerson.setAddPerson(person);
    }

    @PutMapping("/put")
    public void updatePerson(Person putPerson){
        isetNewPerson.setUpdatePerson(putPerson);
    }

    @DeleteMapping("/delete")
    public void removePerson(Person removePerson){
        isetNewPerson.setRemovePerson(removePerson);
    }


}


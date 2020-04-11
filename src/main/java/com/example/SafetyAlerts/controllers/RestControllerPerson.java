package com.example.SafetyAlerts.controllers;

import com.example.SafetyAlerts.dao.GetAll;
import com.example.SafetyAlerts.dao.GetPersonInfo;
import com.example.SafetyAlerts.dao.SetNewPerson;
import com.example.SafetyAlerts.modeles.ObjectFromData;
import com.example.SafetyAlerts.modeles.Person;
import com.example.SafetyAlerts.modeles.PersonAllList;
import com.example.SafetyAlerts.modeles.PersonList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class RestControllerPerson {

@Autowired
    SetNewPerson setNewPerson;

    @PostMapping("/")
    public void addPerson(Person person) {
        setNewPerson.setAddPerson(person);
    }

    @PutMapping("/put")
    public void updatePerson(Person putPerson){
        setNewPerson.setUpdatePerson(putPerson);
    }

    @DeleteMapping("/delete")
    public void removePerson(Person removePerson){
        setNewPerson.setRemovePerson(removePerson);
    }


/*    public @ResponseBody

    ResponseEntity<String> post() {


        return new ResponseEntity<String>("POST Response", HttpStatus.OK);
    }*/

    }


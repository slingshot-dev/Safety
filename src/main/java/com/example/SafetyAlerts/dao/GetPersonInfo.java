package com.example.SafetyAlerts.dao;

import com.example.SafetyAlerts.SafetyAlertsMapper;
import com.example.SafetyAlerts.modeles.ObjectFromData;
import com.example.SafetyAlerts.modeles.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Component
public class GetPersonInfo implements IGetPersonInfo {


    @Override
    public List<Person> getPersonInfo() {

        ObjectFromData objectsFromData = SafetyAlertsMapper.read();
        List<Person> objetPerson = objectsFromData.getPersons();

        //System.out.println(result);
        return objetPerson;
    }
}

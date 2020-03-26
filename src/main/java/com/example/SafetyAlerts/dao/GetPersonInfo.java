package com.example.SafetyAlerts.dao;

import com.example.SafetyAlerts.SafetyAlertsMapper;
import com.example.SafetyAlerts.modeles.ObjectFromData;
import com.example.SafetyAlerts.modeles.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xpath.internal.objects.XNull;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class GetPersonInfo implements IGetPersonInfo {


    @Override
    public List<Person> getPersonFindByName(String firstname, String lastname) {
    List<Person> result = getPersonAll();
    return result.stream()
                .filter(person -> person.getFirstName().contentEquals(firstname)).collect(Collectors.toList()); // Liste passer en flux puis person en argument (firstnmae/lastname envoyé dans la Lambda
/*                .forEach(System.out::println);*/
    }

    @Override
    public List<Person> getPersonAll() {
        ObjectFromData objectsFromData = SafetyAlertsMapper.read();
        return objectsFromData.getPersons();
    }
}

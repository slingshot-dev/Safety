package com.example.SafetyAlerts.dao;


import com.example.SafetyAlerts.SafetyAlertsMapper;
import com.example.SafetyAlerts.modeles.Email;
import com.example.SafetyAlerts.modeles.ObjectFromData;
import com.example.SafetyAlerts.modeles.Person;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

@Component
public class GetCommunityEmailInfo implements IGetCommunityEmailInfo {


    @Override
    public ArrayList<String> getEmail(String city) {

        ArrayList<String> result = new ArrayList<>();

        getPersonAll().forEach(person -> {
            if (person.getCity().contentEquals(city)) {
                result.add(person.getEmail());
            }
        });
            return result;
    }

    @Override
    public List<Person> getPersonAll() {
        ObjectFromData objectsFromData = SafetyAlertsMapper.read();
        return objectsFromData.getPersons();
    }

}

package com.example.SafetyAlerts.dao;


import com.example.SafetyAlerts.SafetyAlertsMapper;
import com.example.SafetyAlerts.modeles.Email;
import com.example.SafetyAlerts.modeles.ObjectFromData;
import com.example.SafetyAlerts.modeles.Person;
import org.springframework.stereotype.Component;

import java.util.*;

import static java.util.stream.Collectors.toMap;

@Component
public class GetCommunityEmailInfo implements IGetCommunityEmailInfo {




    @Override
    public Object getEmail(String city) {

/*        ArrayList<Person> result = new ArrayList<>();*/
        Email email = new Email();
        List<String> result = new ArrayList<>();

        getPersonAll().forEach(person -> {
            if (person.getCity().contentEquals(city)) {
                email.setFirstName(person.getFirstName());
                email.setLastName(person.getLastName());
                email.setEmail(person.getEmail());


                result.add(email.getFirstName());
                result.add(email.getLastName());
                result.add(email.getEmail());


            }
        });
        return result;
    }






/*
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
*/

    @Override
    public List<Person> getPersonAll() {
        ObjectFromData objectsFromData = SafetyAlertsMapper.read();
        return objectsFromData.getPersons();
    }

}

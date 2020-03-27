package com.example.SafetyAlerts.dao;

import com.example.SafetyAlerts.SafetyAlertsMapper;
import com.example.SafetyAlerts.modeles.MedicalRecord;
import com.example.SafetyAlerts.modeles.ObjectFromData;
import com.example.SafetyAlerts.modeles.Person;
import com.example.SafetyAlerts.modeles.PersonMedic;
import com.example.SafetyAlerts.utils.GetAge;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class GetPersonInfo implements IGetPersonInfo {


    @Override
    public ArrayList<String> getPersonFindByName(String firstname, String lastname) {

    List<Person> result = getPersonAll();
        ArrayList<String> result2 = new ArrayList<>();

        result.forEach(person3 -> {
            if (person3.getLastName().contentEquals(lastname)) {
                String firstname2 = person3.getFirstName();
                String lastname2 = person3.getLastName();

                result.forEach(person -> {
                    if (person.getLastName().contentEquals(lastname2) && person.getFirstName().contentEquals(firstname2)) {
                        result2.add(person.getLastName());
                        result2.add(person.getAddress());
                        result2.add(person.getEmail());

                        getMedAll().forEach(person2 -> {
                            if (person2.getLastName().contentEquals(lastname2) && person2.getFirstName().contentEquals(firstname2)) {
                                String birthDate = person2.getBirthdate();
                                String age = GetAge.getAge(birthDate);
                                result2.add(age);
                                result2.add(person2.getMedications().toString());
                                result2.add(person2.getAllergies().toString());
                            }
                        });
                    }
                });
           }
        });
    return result2;
    }

    @Override
    public List<Person> getPersonAll() {
        ObjectFromData objectsFromData = SafetyAlertsMapper.read();
        return objectsFromData.getPersons();
    }
    @Override
    public List<MedicalRecord> getMedAll(){
        ObjectFromData objectsFromData = SafetyAlertsMapper.read();
        return objectsFromData.getMedicalrecords();
    }
}

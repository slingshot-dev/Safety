package com.example.SafetyAlerts.dao;

import com.example.SafetyAlerts.SafetyAlertsMapper;
import com.example.SafetyAlerts.modeles.*;
import com.example.SafetyAlerts.utils.GetAge;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class GetPersonInfo implements IGetPersonInfo {


    /**
     * This URL return from Firstname and lastname, tyhe folowing informations :
     * Lastname, adresse, age, Email, Medicals records.
     *
     * @param firstname
     * @param lastname
     * @return
     */

    @Override
    public PersonList getPersonFindByName(String firstname, String lastname) {

    List<Person> result = getPersonAll();
        ArrayList<PersonUrl> result2 = new ArrayList<>();

        PersonList personList = new PersonList();

        result.forEach(person3 -> {
            if (person3.getLastName().contentEquals(lastname)) {
                String firstname2 = person3.getFirstName();
                String lastname2 = person3.getLastName();

                result.forEach(person -> {
                    if (person.getLastName().contentEquals(lastname2) && person.getFirstName().contentEquals(firstname2)) {

                        PersonUrl personUrl = new PersonUrl();
                        personUrl.setFirstName(person.getFirstName());
                        personUrl.setLastName(person.getLastName());
                        personUrl.setEmail(person.getEmail());

/*                        result2.add(person.getLastName());
                        result2.add(person.getAddress());
                        result2.add(person.getEmail());*/

                        getMedAll().forEach(person2 -> {
                            if (person2.getLastName().contentEquals(lastname2) && person2.getFirstName().contentEquals(firstname2)) {
                                String birthDate = person2.getBirthdate();
                                String age = GetAge.getAge(birthDate);

                                personUrl.setAllergies(person2.getAllergies());
                                personUrl.setMedics(person2.getMedications());
                                personUrl.setAge(age);

                                result2.add(personUrl);

/*                                result2.add(age);
                                result2.add(person2.getMedications().toString());
                                result2.add(person2.getAllergies().toString());*/
                            }
                        });
                    }
                });
           }
        });
        personList.setPersonUrls(result2);
    return personList;
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

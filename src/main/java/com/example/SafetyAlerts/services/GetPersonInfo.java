package com.example.SafetyAlerts.services;

import com.example.SafetyAlerts.dao.IGetAll2;
import com.example.SafetyAlerts.dao.impl.FirestationDAO;
import com.example.SafetyAlerts.dao.impl.MedicDA0;
import com.example.SafetyAlerts.dao.impl.PersonDAO;
import com.example.SafetyAlerts.modeles.*;
import com.example.SafetyAlerts.utils.GetAge;
import org.springframework.stereotype.Component;
import java.util.*;


public class GetPersonInfo {


    /**
     * This URL return from Firstname and lastname, tyhe folowing informations :
     * Lastname, adresse, age, Email, Medicals records.
     *
     * @param firstname
     * @param lastname
     * @return
     */

    IGetAll2<Person> personDAO = new PersonDAO();
    IGetAll2<MedicalRecord> medicDA0 = new MedicDA0();
    IGetAll2<Firestation> firestationDAO = new FirestationDAO();


    public List<PersonUrl> getPersonFindByName(String firstname, String lastname) {

        List<Person> result = personDAO.getAll();
        List<MedicalRecord> resultMedic = medicDA0.getAll();

        List<PersonUrl> result2 = new ArrayList<>();

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

                        resultMedic.forEach(person2 -> {
                            if (person2.getLastName().contentEquals(lastname2) && person2.getFirstName().contentEquals(firstname2)) {
                                String birthDate = person2.getBirthdate();
                                String age = GetAge.getAge(birthDate);

                                personUrl.setAllergies(person2.getAllergies());
                                personUrl.setMedics(person2.getMedications());
                                personUrl.setAge(age);

                                result2.add(personUrl);

                            }
                        });
                    }
                });
            }
        });
        return result2;
    }

}


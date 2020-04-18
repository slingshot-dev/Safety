package com.example.SafetyAlerts.services;

import com.example.SafetyAlerts.dao.IGetAll2;
import com.example.SafetyAlerts.dao.impl.FirestationDAO;
import com.example.SafetyAlerts.dao.impl.MedicDA0;
import com.example.SafetyAlerts.dao.impl.PersonDAO;
import com.example.SafetyAlerts.modeles.*;
import com.example.SafetyAlerts.utils.GetAge;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class GetFlood {


    /**
     * This URL return from FireStation, the folowing informations :
     * Lastname, phone Number, age, Email, Medicals records.
     *
     * @return
     */

    IGetAll2<Person> personDAO = new PersonDAO();
    IGetAll2<MedicalRecord> medicDA0 = new MedicDA0();
    IGetAll2<Firestation> firestationDAO = new FirestationDAO();


    public List<FloodUrl> getFlood(String station) {

        List<Person> result = personDAO.getAll();
        List<Firestation> resultFire = firestationDAO.getAll();
        List<MedicalRecord> resultMedic = medicDA0.getAll();

        List<FloodUrl> result2 = new ArrayList<>();


        resultFire.forEach(firestation -> {
            if (firestation.getStation().contentEquals(station)) {
                String adress = firestation.getAddress();
                String numStation = firestation.getStation();

                result.forEach(person -> {
                    if (person.getAddress().contentEquals(adress)) {
                        String firstname = person.getFirstName();
                        String lastname = person.getLastName();

                        FloodUrl floodUrl = new FloodUrl();
                        floodUrl.setFirstName(person.getFirstName());
                        floodUrl.setLastName(person.getLastName());
                        floodUrl.setAddress(person.getAddress());
                        floodUrl.setPhone(person.getPhone());
                        floodUrl.setFirestationNumber(numStation);

                        resultMedic.forEach(person2 -> {
                            if (person2.getLastName().contentEquals(lastname) && person2.getFirstName().contentEquals(firstname)) {
                                String birthDate = person2.getBirthdate();
                                String age = GetAge.getAge(birthDate);

                                floodUrl.setAge(age);
                                floodUrl.setMedics(person2.getMedications());
                                floodUrl.setAllergies(person2.getAllergies());

                                result2.add(floodUrl);
                            }
                        });
                    }
                });
            }
        });
        return result2;
    }

}


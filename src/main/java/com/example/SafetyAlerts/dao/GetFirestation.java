package com.example.SafetyAlerts.dao;

import com.example.SafetyAlerts.modeles.*;
import com.example.SafetyAlerts.utils.GetAge;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class GetFirestation extends GetAll implements IGetFirestation {

    /**
     * This URL return from Station number, the folowing informations :
     * FirstName, Lastname, address, phone Number, age, Medicals records, Station Number.
     * A count of adults en Childrens in this area
     *
     * @return
     */


    @Override
    public FirestationUrl getFirestation(String station) {

        List<Person> result = getPersonAll();
        List<Firestation> resultFire = getFireAll();

        ArrayList<PersonParFirestation> result2 = new ArrayList<>();
        FirestationUrl firestationUrl = new FirestationUrl();

        AtomicInteger ageEnfant = new AtomicInteger();
        AtomicInteger ageAdult = new AtomicInteger();


        resultFire.forEach(firestation -> {
            if (firestation.getStation().contentEquals(station)) {
                String address = firestation.getAddress();

                result.forEach(person -> {
                    if (person.getAddress().contentEquals(address)) {
                        String firstname = person.getFirstName();
                        String lastname = person.getLastName();

                        PersonParFirestation personParFirestation = new PersonParFirestation();

                        personParFirestation.setFirstName(person.getFirstName());
                        personParFirestation.setLastName(person.getLastName());
                        personParFirestation.setAddress(person.getAddress());
                        personParFirestation.setPhone(person.getPhone());

                        result2.add(personParFirestation);
                        firestationUrl.setPersons(result2);

                        getMedAll().forEach(person2 -> {
                            if (person2.getLastName().contentEquals(lastname) && person2.getFirstName().contentEquals(firstname)) {
                                String birthDate = person2.getBirthdate();
                                String age = GetAge.getAge(birthDate);

                                int ageInt = Integer.parseInt(age);
                                if (ageInt <= 18){
                                    ageEnfant.getAndIncrement();
                                } else {
                                    ageAdult.getAndIncrement();
                                }
                                firestationUrl.setNbAdults(String.valueOf(ageAdult.get()));
                                firestationUrl.setNbEnfants(String.valueOf(ageEnfant.get()));
                            }
                        });
                     }
                });
            }
        });
        return firestationUrl;
    }
}

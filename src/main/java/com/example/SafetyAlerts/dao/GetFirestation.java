package com.example.SafetyAlerts.dao;

import com.example.SafetyAlerts.SafetyAlertsMapper;
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
    public FirestationList getFirestation(String station) {

        List<Person> result = getPersonAll();
        List<Firestation> resultFire = getFireAll();
        ArrayList<FirestationUrl> result2 = new ArrayList<>();
        AtomicInteger ageAdult = new AtomicInteger();
        AtomicInteger ageEnfant = new AtomicInteger();

        FirestationList firestationList = new FirestationList();

        resultFire.forEach(firestation -> {
            if (firestation.getStation().contentEquals(station)) {
                String address = firestation.getAddress();

                result.forEach(person -> {
                    if (person.getAddress().contentEquals(address)) {
                        String firstname = person.getFirstName();
                        String lastname = person.getLastName();

                        FirestationUrl firestationUrl = new FirestationUrl();

                        firestationUrl.setFirstName(person.getFirstName());
                        firestationUrl.setLastName(person.getLastName());
                        firestationUrl.setAddress(person.getAddress());
                        firestationUrl.setPhone(person.getPhone());

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
                                firestationUrl.setNbAdults(String.valueOf(ageAdult));
                                firestationUrl.setNbEnfants(String.valueOf(ageEnfant));

                                result2.add(firestationUrl);

                            }
                        });
                    }
                });
            }
        });
        System.out.println(ageAdult +" "+ageEnfant);

        firestationList.setFirestations(result2);
        return firestationList;
    }

}

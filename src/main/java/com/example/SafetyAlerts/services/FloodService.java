package com.example.SafetyAlerts.services;

import com.example.SafetyAlerts.dao.IGetAll;
import com.example.SafetyAlerts.modeles.*;
import com.example.SafetyAlerts.utils.GetAge;
import org.springframework.stereotype.Service;

import java.util.*;

/** Classe de creation de la liste FloodUrl :
 * Cette url retourne une liste de tous les foyers desservis par la caserne. Cette liste regroupe les
 * personnes par adresse. Elle inclue le nom, le numéro de téléphone et l'âge des habitants, et
 * fais figurer leurs antécédents médicaux (médicaments, posologie et allergies) à côté de chaque nom.
 */

@Service
public class FloodService extends CommonsServices {


    public FloodService(IGetAll<Person> personDAO, IGetAll<Firestation> firestationDAO, IGetAll<MedicalRecord> medicDA0) {
        super(personDAO, firestationDAO, medicDA0);
    }

    /**
     * @param station : Parametre du numero de Station
     * @return : Retourne la Liste FloodUrl
     */

    public List<FloodUrl> getFlood(String station) {

        List<FloodUrl> result2 = new ArrayList<>();


        getFirestationAll().forEach(firestation -> {
            if (firestation.getStation().contentEquals(station)) {
                String adress = firestation.getAddress();
                String numStation = firestation.getStation();

                getPersonAll().forEach(person -> {
                    if (person.getAddress().contentEquals(adress)) {
                        String firstname = person.getFirstName();
                        String lastname = person.getLastName();

                        FloodUrl floodUrl = new FloodUrl();
                        floodUrl.setFirstName(person.getFirstName());
                        floodUrl.setLastName(person.getLastName());
                        floodUrl.setAddress(person.getAddress());
                        floodUrl.setPhone(person.getPhone());
                        floodUrl.setFirestationNumber(numStation);

                        getMedicAll().forEach(person2 -> {
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


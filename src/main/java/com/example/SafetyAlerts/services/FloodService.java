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

    public List<PersonInfos> getFlood(String station) {

        List<PersonInfos> result2 = new ArrayList<>();

        getFirestationAll().forEach(firestation -> {
            if (firestation.getStation().contentEquals(station)) {
                String adress = firestation.getAddress();
                String numStation = firestation.getStation();

                getPersonAll().forEach(person -> {
                    if (person.getAddress().contentEquals(adress)) {
                        String firstname = person.getFirstName();
                        String lastname = person.getLastName();

                        PersonInfos personInfos = new PersonInfos();

                        personInfos.setFirstName(person.getFirstName());
                        personInfos.setLastName(person.getLastName());
                        personInfos.setAddress(person.getAddress());
                        personInfos.setPhone(person.getPhone());
                        personInfos.setStationNumber(numStation);

                        getMedicAll().forEach(person2 -> {
                            if (person2.getLastName().contentEquals(lastname) && person2.getFirstName().contentEquals(firstname)) {
                                String birthDate = person2.getBirthdate();
                                String age = GetAge.getAge(birthDate);

                                personInfos.setAge(age);
                                personInfos.setMedics(person2.getMedications());
                                personInfos.setAllergies(person2.getAllergies());

                                result2.add(personInfos);
                            }
                        });
                    }
                });
            }
        });
        return result2;
    }

}


package com.example.SafetyAlerts.services;

import com.example.SafetyAlerts.dao.IGetAll;
import com.example.SafetyAlerts.modeles.*;
import org.springframework.stereotype.Service;

import java.util.*;

/** Classe de creation de la liste PhoneAlertUrl :
 * Cette url retourne une liste des numéros de téléphone des résidents desservis par la caserne de
 * pompiers. Nous l'utilisons pour envoyer des messages texte d'urgence à des foyers spécifiques.
 */

@Service
public class PhoneAlertService extends CommonsServices {


    public PhoneAlertService(IGetAll<Person> personDAO, IGetAll<Firestation> firestationDAO, IGetAll<MedicalRecord> medicDA0) {
        super(personDAO, firestationDAO, medicDA0);
    }

    /**
     * @param station : parametre du numero de Station
     * @return : Retourne la liste PhonealertUrl.
     */

    public List<PhoneAlertUrl> getPhoneAlert(String station) {

        ArrayList<PhoneAlertUrl> result2 = new ArrayList<>();

        getFirestationAll().forEach(firestation -> {
            if (firestation.getStation().contentEquals(station)) {
                String adress = firestation.getAddress();

                getPersonAll().forEach(person -> {
                    if (person.getAddress().contentEquals(adress)) {

                        PhoneAlertUrl phoneAlertUrl = new PhoneAlertUrl();
                        phoneAlertUrl.setFirstName(person.getFirstName());
                        phoneAlertUrl.setLastName(person.getLastName());
                        phoneAlertUrl.setPhone(person.getPhone());

                        result2.add(phoneAlertUrl);
                    }
                });
            }
        });
        return result2;
    }
}


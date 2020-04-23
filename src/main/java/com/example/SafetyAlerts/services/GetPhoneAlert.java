package com.example.SafetyAlerts.services;

import com.example.SafetyAlerts.dao.IGetAll2;
import com.example.SafetyAlerts.modeles.*;
import org.springframework.stereotype.Service;

import java.util.*;

/** Classe de creation de la liste PhoneAlertUrl :  This URL return from FireStation, the folowing informations : Firstname, Lastname, phone Number.
 */

@Service
public class GetPhoneAlert {

    private final IGetAll2<Person> personDAO;
    private final IGetAll2<Firestation> firestationDAO;

    public GetPhoneAlert(IGetAll2<Person> personDAO, IGetAll2<Firestation> firestationDAO, IGetAll2<MedicalRecord> medicDA0) {
        this.personDAO = personDAO;
        this.firestationDAO = firestationDAO;
    }

    /**
     * @param station : parametre du numero de Station
     * @return : Retourne la liste PhonealertUrl.
     */

    public List<PhoneAlertUrl> getPhoneAlert(String station) {

        List<Person> result = personDAO.getAll();
        List<Firestation> resultFire = firestationDAO.getAll();
        ArrayList<PhoneAlertUrl> result2 = new ArrayList<>();

        resultFire.forEach(firestation -> {
            if (firestation.getStation().contentEquals(station)) {
                String adress = firestation.getAddress();

                result.forEach(person -> {
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


package com.example.SafetyAlerts.services;

import com.example.SafetyAlerts.dao.IGetAll2;
import com.example.SafetyAlerts.dao.impl.FirestationDAO;
import com.example.SafetyAlerts.dao.impl.MedicDA0;
import com.example.SafetyAlerts.dao.impl.PersonDAO;
import com.example.SafetyAlerts.modeles.*;
import org.springframework.stereotype.Component;
import java.util.*;

public class GetPhoneAlert {


    /**
     * This URL return from FireStation, the folowing informations :
     * Firstname, Lastname, phone Number.
     *
     * @return
     */

    IGetAll2<Person> personDAO = new PersonDAO();
    IGetAll2<MedicalRecord> medicDA0 = new MedicDA0();
    IGetAll2<Firestation> firestationDAO = new FirestationDAO();


    public List<PhoneAlertUrl> getPhoneAlert(String station) {

        List<Person> result = personDAO.getAll();
        List<Firestation> resultFire = firestationDAO.getAll();
        List<MedicalRecord> resultMedic = medicDA0.getAll();
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


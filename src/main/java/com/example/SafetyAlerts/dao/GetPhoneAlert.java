package com.example.SafetyAlerts.dao;

import com.example.SafetyAlerts.SafetyAlertsMapper;
import com.example.SafetyAlerts.modeles.*;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class GetPhoneAlert implements IGetPhoneAlert {


    /**
     * This URL return from FireStation, the folowing informations :
     * Firstname, Lastname, phone Number.
     *
     * @return
     */

    @Override
    public PhoneAlertList getPhoneAlert(String station) {

        List<Person> result = getPersonAll();
        List<Firestation> resultFire = getFireAll();
        ArrayList<PhoneAlertUrl> result2 = new ArrayList<>();

        PhoneAlertList phoneAlertList = new PhoneAlertList();


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
/*                        result2.add(person.getLastName());
                        result2.add(person.getPhone());*/
                    }
                });
            }
        });

        phoneAlertList.setPhoneAlertUrls(result2);
        return phoneAlertList;
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

    @Override
    public List<Firestation> getFireAll(){
        ObjectFromData objectsFromData = SafetyAlertsMapper.read();
        return objectsFromData.getFirestations();
    }


}

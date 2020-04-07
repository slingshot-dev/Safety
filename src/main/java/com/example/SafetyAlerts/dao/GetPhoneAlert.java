package com.example.SafetyAlerts.dao;

import com.example.SafetyAlerts.SafetyAlertsMapper;
import com.example.SafetyAlerts.modeles.*;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class GetPhoneAlert extends GetAll implements IGetPhoneAlert {


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
                    }
                });
            }
        });

        phoneAlertList.setPhoneAlertUrls(result2);
        return phoneAlertList;
    }

}

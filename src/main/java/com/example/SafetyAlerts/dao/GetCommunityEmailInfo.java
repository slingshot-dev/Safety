package com.example.SafetyAlerts.dao;


import com.example.SafetyAlerts.SafetyAlertsMapper;
import com.example.SafetyAlerts.modeles.EmailUrl;
import com.example.SafetyAlerts.modeles.EmailList;
import com.example.SafetyAlerts.modeles.ObjectFromData;
import com.example.SafetyAlerts.modeles.Person;
import org.springframework.stereotype.Component;

import java.util.*;

import static java.util.stream.Collectors.toMap;

@Component
public class GetCommunityEmailInfo extends GetAll implements IGetCommunityEmailInfo {


    @Override
    public EmailList getEmail(String city) {

        List<EmailUrl> result = new ArrayList<>();
        EmailList emailList = new EmailList();

        getPersonAll().forEach(person -> {
            if (person.getCity().contentEquals(city)) {
                EmailUrl emailUrl = new EmailUrl();
                emailUrl.setFirstName(person.getFirstName());
                emailUrl.setLastName(person.getLastName());
                emailUrl.setEmail(person.getEmail());
                result.add(emailUrl);
            }
        });
        emailList.setEmailUrls(result);
        return emailList;
    }

}

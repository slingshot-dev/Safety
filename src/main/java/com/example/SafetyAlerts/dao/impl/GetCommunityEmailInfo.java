package com.example.SafetyAlerts.dao.impl;


import com.example.SafetyAlerts.dao.IGetCommunityEmailInfo;
import com.example.SafetyAlerts.dao.impl.GetAll;
import com.example.SafetyAlerts.modeles.EmailUrl;
import com.example.SafetyAlerts.modeles.EmailList;
import org.springframework.stereotype.Component;
import java.util.*;


@Component
public class GetCommunityEmailInfo extends GetAll implements IGetCommunityEmailInfo {


    private final EmailList emailList;
    public GetCommunityEmailInfo(EmailList emailList) {
        this.emailList = emailList;
    }


    @Override
    public EmailList getEmail(String city) {

        List<EmailUrl> result = new ArrayList<>();

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

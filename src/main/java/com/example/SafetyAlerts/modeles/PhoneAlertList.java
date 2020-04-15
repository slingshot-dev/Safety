package com.example.SafetyAlerts.modeles;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PhoneAlertList {

    ArrayList<PhoneAlertUrl> phoneAlertUrls = new ArrayList<>();

    public ArrayList<PhoneAlertUrl> getPhoneAlertUrls() {
        return phoneAlertUrls;
    }

    public void setPhoneAlertUrls(ArrayList<PhoneAlertUrl> phoneAlertUrls) {
        this.phoneAlertUrls = phoneAlertUrls;
    }
}

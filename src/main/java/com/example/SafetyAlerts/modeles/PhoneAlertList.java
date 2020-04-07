package com.example.SafetyAlerts.modeles;

import java.util.ArrayList;

public class PhoneAlertList {

    ArrayList<PhoneAlertUrl> phoneAlertUrls = new ArrayList<>();

    public ArrayList<PhoneAlertUrl> getPhoneAlertUrls() {
        return phoneAlertUrls;
    }

    public void setPhoneAlertUrls(ArrayList<PhoneAlertUrl> phoneAlertUrls) {
        this.phoneAlertUrls = phoneAlertUrls;
    }
}

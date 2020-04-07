package com.example.SafetyAlerts.modeles;

import java.util.ArrayList;
import java.util.List;

public class EmailList {
    List<EmailUrl> emailUrls = new ArrayList<>();

    public List<EmailUrl> getEmailUrls() {
        return emailUrls;
    }

    public void setEmailUrls(List<EmailUrl> emailUrls) {
        this.emailUrls = emailUrls;
    }
}

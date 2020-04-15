package com.example.SafetyAlerts.modeles;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmailList {
    List<EmailUrl> emailUrls = new ArrayList<>();

    public List<EmailUrl> getEmailUrls() {
        return emailUrls;
    }

    public void setEmailUrls(List<EmailUrl> emailUrls) {
        this.emailUrls = emailUrls;
    }
}

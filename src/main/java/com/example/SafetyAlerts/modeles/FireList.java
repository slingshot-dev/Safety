package com.example.SafetyAlerts.modeles;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FireList {

    List<FireUrl> fireUrls = new ArrayList<>();

    public List<FireUrl> getFireUrls() {
        return fireUrls;
    }

    public void setFireUrls(List<FireUrl> fireUrls) {
        this.fireUrls = fireUrls;
    }
}

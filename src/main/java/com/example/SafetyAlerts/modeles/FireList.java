package com.example.SafetyAlerts.modeles;

import java.util.ArrayList;
import java.util.List;

public class FireList {

    List<FireUrl> fireUrls = new ArrayList<>();

    public List<FireUrl> getFireUrls() {
        return fireUrls;
    }

    public void setFireUrls(List<FireUrl> fireUrls) {
        this.fireUrls = fireUrls;
    }
}

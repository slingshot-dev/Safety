package com.example.SafetyAlerts.modeles;

import java.util.ArrayList;
import java.util.List;

public class FloodList {

    List<FloodUrl> floodUrls = new ArrayList<>();

    public List<FloodUrl> getFloodUrls() {
        return floodUrls;
    }

    public void setFloodUrls(List<FloodUrl> floodUrls) {
        this.floodUrls = floodUrls;
    }
}

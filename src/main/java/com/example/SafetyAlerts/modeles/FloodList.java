package com.example.SafetyAlerts.modeles;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FloodList {

    List<FloodUrl> floodUrls = new ArrayList<>();

    public List<FloodUrl> getFloodUrls() {
        return floodUrls;
    }

    public void setFloodUrls(List<FloodUrl> floodUrls) {
        this.floodUrls = floodUrls;
    }
}

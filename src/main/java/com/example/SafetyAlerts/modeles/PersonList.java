package com.example.SafetyAlerts.modeles;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonList {

    List<PersonUrl> personUrls = new ArrayList<>();

    public List<PersonUrl> getPersonUrls() {
        return personUrls;
    }

    public void setPersonUrls(List<PersonUrl> personUrls) {
        this.personUrls = personUrls;
    }
}

package com.example.SafetyAlerts.modeles;

import java.util.ArrayList;
import java.util.List;

public class PersonList {

    List<PersonUrl> personUrls = new ArrayList<>();

    public List<PersonUrl> getPersonUrls() {
        return personUrls;
    }

    public void setPersonUrls(List<PersonUrl> personUrls) {
        this.personUrls = personUrls;
    }
}

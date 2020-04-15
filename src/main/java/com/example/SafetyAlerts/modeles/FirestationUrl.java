package com.example.SafetyAlerts.modeles;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FirestationUrl {

    private List<PersonParFirestation> persons;
    private String nbAdults;
    private String nbEnfants;


    public List<PersonParFirestation> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonParFirestation> persons) {
        this.persons = persons;
    }

    public String getNbAdults() {
        return nbAdults;
    }

    public void setNbAdults(String nbAdults) {
        this.nbAdults = nbAdults;
    }

    public String getNbEnfants() {
        return nbEnfants;
    }

    public void setNbEnfants(String nbEnfants) {
        this.nbEnfants = nbEnfants;
    }
}

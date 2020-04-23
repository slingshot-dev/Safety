package com.example.SafetyAlerts.modeles;

import java.util.List;

public class ChildAlertUrl {

    List<Child> enfantFoyer;
    List<Person> personFoyer;

    public List<Child> getEnfantFoyer() {
        return enfantFoyer;
    }

    public void setEnfantFoyer(List<Child> enfantFoyer) {
        this.enfantFoyer = enfantFoyer;
    }

    public List<Person> getPersonFoyer() {
        return personFoyer;
    }

    public void setPersonFoyer(List<Person> personFoyer) {
        this.personFoyer = personFoyer;
    }
}

package com.example.SafetyAlerts.modeles;

import java.util.ArrayList;
import java.util.List;

public class ChildAlertUrl {
    private String firstName;
    private String lastName;
    private String age;
    List<Person> personParFoyers;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public List<Person> getPersonParFoyers() {
        return personParFoyers;
    }

    public void setPersonParFoyers(List<Person> personParFoyers) {
        this.personParFoyers = personParFoyers;
    }
}

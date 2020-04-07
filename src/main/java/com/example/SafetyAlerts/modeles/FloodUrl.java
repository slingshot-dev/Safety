package com.example.SafetyAlerts.modeles;

import java.util.List;

public class FloodUrl {
    private  String firestationNumber;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String age;
    private List<String> medics;
    private List<String> allergies;

    public String getFirestationNumber() {
        return firestationNumber;
    }

    public void setFirestationNumber(String firestationNumber) {
        this.firestationNumber = firestationNumber;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public List<String> getMedics() {
        return medics;
    }

    public void setMedics(List<String> medics) {
        this.medics = medics;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }
}

package com.example.SafetyAlerts.modeles;

import java.util.ArrayList;

public class FirestationList {

        ArrayList<FirestationUrl> firestations = new ArrayList<>();

    public ArrayList<FirestationUrl> getFirestations() {
        return firestations;
    }

    public void setFirestations(ArrayList<FirestationUrl> firestations) {
        this.firestations = firestations;
    }
}

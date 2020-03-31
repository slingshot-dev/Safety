package com.example.SafetyAlerts.dao;

import com.example.SafetyAlerts.modeles.Firestation;
import com.example.SafetyAlerts.modeles.MedicalRecord;
import com.example.SafetyAlerts.modeles.Person;

import java.util.ArrayList;
import java.util.List;

public interface IGetFlood {

    ArrayList<String> getFlood(String station);

    List<Person> getPersonAll();

    List<MedicalRecord> getMedAll();

    List<Firestation> getFireAll();


}
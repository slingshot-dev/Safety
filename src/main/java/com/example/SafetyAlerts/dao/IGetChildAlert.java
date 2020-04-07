package com.example.SafetyAlerts.dao;

import com.example.SafetyAlerts.modeles.ChildAlertList;
import com.example.SafetyAlerts.modeles.Firestation;
import com.example.SafetyAlerts.modeles.MedicalRecord;
import com.example.SafetyAlerts.modeles.Person;

import java.util.List;

public interface IGetChildAlert {

    ChildAlertList getChildAlert(String address);

    List<Person> getPersonAll();

    List<MedicalRecord> getMedAll();

    List<Firestation> getFireAll();

}

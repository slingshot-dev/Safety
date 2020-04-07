package com.example.SafetyAlerts.dao;

import com.example.SafetyAlerts.modeles.*;

import java.util.List;

public interface IGetPhoneAlert {

    PhoneAlertList getPhoneAlert(String station);

    List<Person> getPersonAll();

    List<MedicalRecord> getMedAll();

    List<Firestation> getFireAll();
}

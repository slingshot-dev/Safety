package com.example.SafetyAlerts.dao;

import com.example.SafetyAlerts.modeles.Firestation;
import com.example.SafetyAlerts.modeles.FirestationList;
import com.example.SafetyAlerts.modeles.MedicalRecord;
import com.example.SafetyAlerts.modeles.Person;

import java.util.List;

public interface IGetFirestation {

    FirestationList getFirestation(String station);

}

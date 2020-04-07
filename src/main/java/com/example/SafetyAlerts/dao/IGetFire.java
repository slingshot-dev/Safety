package com.example.SafetyAlerts.dao;

import com.example.SafetyAlerts.modeles.FireList;
import com.example.SafetyAlerts.modeles.Firestation;
import com.example.SafetyAlerts.modeles.MedicalRecord;
import com.example.SafetyAlerts.modeles.Person;

import java.util.List;

public interface IGetFire {

    FireList getFire(String address);

}

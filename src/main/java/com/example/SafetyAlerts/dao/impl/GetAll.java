package com.example.SafetyAlerts.dao.impl;

import com.example.SafetyAlerts.dao.IGetAll;
import com.example.SafetyAlerts.utils.SafetyAlertsMapper;
import com.example.SafetyAlerts.modeles.Firestation;
import com.example.SafetyAlerts.modeles.MedicalRecord;
import com.example.SafetyAlerts.modeles.ObjectFromData;
import com.example.SafetyAlerts.modeles.Person;
import org.springframework.stereotype.Component;

import java.util.List;


public class GetAll implements IGetAll {

    @Override
    public List<Person> getPersonAll() {
        ObjectFromData objectsFromData = SafetyAlertsMapper.read();
        return objectsFromData.getPersons();
    }

    @Override
    public List<MedicalRecord> getMedAll() {
        ObjectFromData objectsFromData = SafetyAlertsMapper.read();
        return objectsFromData.getMedicalrecords();
    }

    @Override
    public List<Firestation> getFireAll() {
        ObjectFromData objectsFromData = SafetyAlertsMapper.read();
        return objectsFromData.getFirestations();
    }




}

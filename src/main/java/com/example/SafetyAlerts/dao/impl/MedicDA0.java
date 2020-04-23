package com.example.SafetyAlerts.dao.impl;

import com.example.SafetyAlerts.dao.IGetAll2;
import com.example.SafetyAlerts.modeles.MedicalRecord;
import com.example.SafetyAlerts.modeles.ObjectFromData;
import com.example.SafetyAlerts.utils.SafetyAlertsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;


@Repository("CMedicDAO")
public class MedicDA0 implements IGetAll2<MedicalRecord> {
    private List<MedicalRecord> medicalRecords = new ArrayList<>();

    public MedicDA0() {
        medicalRecords.add(new MedicalRecord());
    }

    @Autowired
    SafetyAlertsMapper safetyAlertsMapper;


    @Override
    public List<MedicalRecord> getAll() {
        ObjectFromData objectsFromData = safetyAlertsMapper.read();
        return objectsFromData.getMedicalrecords();
    }

    @Override
    public void save(MedicalRecord medicalRecord) {
        ObjectFromData objectsFromData = safetyAlertsMapper.read();
        objectsFromData.getMedicalrecords().add(medicalRecord);
        SafetyAlertsMapper.write(objectsFromData);
    }

    @Override
    public void update(MedicalRecord medicalRecord, int index) {

        ObjectFromData objectsFromData = safetyAlertsMapper.read();
        medicalRecords = objectsFromData.getMedicalrecords();
        medicalRecords.get(index).setFirstName(medicalRecord.getFirstName());
        medicalRecords.get(index).setLastName(medicalRecord.getLastName());
        medicalRecords.get(index).setBirthdate(medicalRecord.getBirthdate());
        medicalRecords.get(index).setMedications(medicalRecord.getMedications());
        medicalRecords.get(index).setAllergies(medicalRecord.getAllergies());

        objectsFromData.setMedicalrecords(medicalRecords);
        SafetyAlertsMapper.write(objectsFromData);
    }

    @Override
    public void delete(List<MedicalRecord> medicalRecord) {

        ObjectFromData objectsFromData = safetyAlertsMapper.read();
        objectsFromData.setMedicalrecords(medicalRecord);
        SafetyAlertsMapper.write(objectsFromData);
    }
}

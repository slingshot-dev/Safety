package com.example.SafetyAlerts.dao.impl;

import com.example.SafetyAlerts.dao.IGetAll2;
import com.example.SafetyAlerts.modeles.Firestation;
import com.example.SafetyAlerts.modeles.ObjectFromData;
import com.example.SafetyAlerts.utils.SafetyAlertsMapper;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;


@Repository("CFirestationDAO")
public class FirestationDAO implements IGetAll2<Firestation> {

    private List<Firestation> firestations = new ArrayList<>();
    private final SafetyAlertsMapper safetyAlertsMapper;

    public FirestationDAO(SafetyAlertsMapper safetyAlertsMapper) {
        this.safetyAlertsMapper = safetyAlertsMapper;
        firestations.add(new Firestation());
    }

    @Override
    public List<Firestation> getAll() {
        ObjectFromData objectsFromData = safetyAlertsMapper.read();
        return objectsFromData.getFirestations();
    }

    @Override
    public void save(Firestation firestation) {
        ObjectFromData objectsFromData = safetyAlertsMapper.read();
        objectsFromData.getFirestations().add(firestation);
        SafetyAlertsMapper.write(objectsFromData);
    }

    @Override
    public void update(Firestation firestation, int index) {
        ObjectFromData objectsFromData = safetyAlertsMapper.read();
        firestations = objectsFromData.getFirestations();
        firestations.get(index).setAddress(firestation.getAddress());
        firestations.get(index).setStation(firestation.getStation());

        objectsFromData.setFirestations(firestations);
        SafetyAlertsMapper.write(objectsFromData);
    }

    @Override
    public void delete(List<Firestation> firestation) {
        ObjectFromData objectsFromData = safetyAlertsMapper.read();
        objectsFromData.setFirestations(firestation);
        SafetyAlertsMapper.write(objectsFromData);
    }
}

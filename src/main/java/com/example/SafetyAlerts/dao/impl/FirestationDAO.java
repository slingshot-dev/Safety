package com.example.SafetyAlerts.dao.impl;

import com.example.SafetyAlerts.dao.IGetAll2;
import com.example.SafetyAlerts.modeles.Firestation;
import com.example.SafetyAlerts.modeles.ObjectFromData;
import com.example.SafetyAlerts.modeles.Person;
import com.example.SafetyAlerts.utils.SafetyAlertsMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository("CFirestationDAO")
public class FirestationDAO implements IGetAll2<Firestation> {

    private List<Firestation> firestations = new ArrayList<>();

    public FirestationDAO() {
        firestations.add(new Firestation());

    }


    @Override
    public Optional<Firestation> get(long id) {
        return Optional.ofNullable(firestations.get((int) id));
    }

    @Override
    public List<Firestation> getAll() {
        ObjectFromData objectsFromData = SafetyAlertsMapper.read();
        firestations =  objectsFromData.getFirestations();
        return firestations;
    }

    @Override
    public void save(Firestation firestation) {
        ObjectFromData objectsFromData = SafetyAlertsMapper.read();
        objectsFromData.getFirestations().add(firestation);
        SafetyAlertsMapper.write(objectsFromData);
    }

    @Override
    public void update(Firestation firestation, int index) {
        ObjectFromData objectsFromData = SafetyAlertsMapper.read();
        firestations =  objectsFromData.getFirestations();
        firestations.get(index).setAddress(firestation.getAddress());
        firestations.get(index).setStation(firestation.getStation());

        objectsFromData.setFirestations(firestations);
        SafetyAlertsMapper.write(objectsFromData);

    }

    @Override
    public void delete(List<Firestation> firestation) {

        ObjectFromData objectsFromData = SafetyAlertsMapper.read();
        objectsFromData.setFirestations(firestation);
        SafetyAlertsMapper.write(objectsFromData);

    }


}

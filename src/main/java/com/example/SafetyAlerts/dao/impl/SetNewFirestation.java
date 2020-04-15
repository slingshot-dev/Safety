package com.example.SafetyAlerts.dao.impl;

import com.example.SafetyAlerts.dao.ISetNewFirestation;
import com.example.SafetyAlerts.utils.SafetyAlertsMapper;
import com.example.SafetyAlerts.modeles.Firestation;
import com.example.SafetyAlerts.modeles.ObjectFromData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SetNewFirestation implements ISetNewFirestation {



    @Override
    public void setAddFirestation(Firestation addFirestation) {

        ObjectFromData objectFromDatas = SafetyAlertsMapper.read();

        // Ajout d'un objet complet(une firestation) a la Liste de Firestation.
        objectFromDatas.getFirestations().add(addFirestation);
        SafetyAlertsMapper.write(objectFromDatas);

    }

    @Override
    public void setUpdateFirestation(Firestation UpdateFirestation) {

        ObjectFromData objectFromDatas = SafetyAlertsMapper.read();

        List<Firestation> resultFire = objectFromDatas.getFirestations();

        String address = UpdateFirestation.getAddress();

        // Modification du numero d'une station en fonction de son adresse
        resultFire.forEach(firestation -> {
            if (firestation.getAddress().contentEquals(address)) {
                int index = resultFire.indexOf(firestation);

                // mise a jour de la firestation
                resultFire.get(index).setStation(UpdateFirestation.getStation());

            }
            });

        // Modification de l'objet Firestation a la Liste de Firestation.
                 // objectFromDatas.setFirestations(resultFire);
        SafetyAlertsMapper.write(objectFromDatas);

    }

    @Override
    public void setRemoveFirestation(Firestation removeFirestation) {

        ObjectFromData objectFromDatas = SafetyAlertsMapper.read();
        List<Firestation> resultFire = objectFromDatas.getFirestations();

        String address = removeFirestation.getAddress();
        String station = removeFirestation.getStation();

        // Suppression d'une personne dans Person
        List<Firestation> filteredList = resultFire.stream()
                .filter(fire1 -> (!fire1.getAddress()
                        .contentEquals(address) && !fire1.getStation()
                        .contentEquals(station)))
                .collect(Collectors.toList());
        objectFromDatas.setFirestations(filteredList);
        SafetyAlertsMapper.write(objectFromDatas);

    }

}
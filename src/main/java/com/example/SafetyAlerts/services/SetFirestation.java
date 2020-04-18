package com.example.SafetyAlerts.services;

import com.example.SafetyAlerts.dao.IGetAll2;
import com.example.SafetyAlerts.dao.impl.FirestationDAO;
import com.example.SafetyAlerts.dao.impl.MedicDA0;
import com.example.SafetyAlerts.dao.impl.PersonDAO;
import com.example.SafetyAlerts.modeles.Firestation;
import com.example.SafetyAlerts.modeles.MedicalRecord;
import com.example.SafetyAlerts.modeles.ObjectFromData;
import com.example.SafetyAlerts.modeles.Person;
import com.example.SafetyAlerts.utils.SafetyAlertsMapper;

import java.util.List;
import java.util.stream.Collectors;

public class SetFirestation {

    IGetAll2<Person> personDAO = new PersonDAO();
    IGetAll2<MedicalRecord> medicDA0 = new MedicDA0();
    IGetAll2<Firestation> firestationDAO = new FirestationDAO();


    public void setAddFirestation(Firestation addFirestation) {


        // Ajout d'un objet complet(une firestation) a la Liste de Firestation.

        firestationDAO.save(addFirestation);

    }

    public void setUpdateFirestation(Firestation UpdateFirestation) {

        List<Firestation> resultFire = firestationDAO.getAll();

        String address = UpdateFirestation.getAddress();
        String station = UpdateFirestation.getStation();

        // Modification du numero d'une station en fonction de son adresse
        resultFire.forEach(firestation -> {
            if (firestation.getAddress().contentEquals(address)) {
                int index = resultFire.indexOf(firestation);

                // mise a jour de la firestation
                firestationDAO.update(UpdateFirestation, index);

            }
        });

        // Modification de l'adresse d'une station en fonction de son numero
        resultFire.forEach(firestation -> {
            if (firestation.getStation().contentEquals(station)) {
                int index = resultFire.indexOf(firestation);

                // mise a jour de la firestation
                firestationDAO.update(UpdateFirestation, index);

            }
        });

    }


    public void setRemoveFirestation(Firestation removeFirestation) {

        List<Firestation> resultFire = firestationDAO.getAll();

        String address = removeFirestation.getAddress();
        String station = removeFirestation.getStation();

        // Suppression d'une firestation dans Firestation
        List<Firestation> filteredList = resultFire.stream()
                .filter(fire1 -> (!fire1.getAddress()
                        .contentEquals(address) && !fire1.getStation()
                        .contentEquals(station)))
                .collect(Collectors.toList());

        firestationDAO.delete(filteredList);

    }


}

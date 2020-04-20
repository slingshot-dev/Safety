package com.example.SafetyAlerts.services;

import com.example.SafetyAlerts.dao.IGetAll2;
import com.example.SafetyAlerts.modeles.Firestation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetFirestation {


    private final IGetAll2<Firestation> firestationDAO;

    public SetFirestation(IGetAll2<Firestation> firestationDAO) {
        this.firestationDAO = firestationDAO;
    }


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

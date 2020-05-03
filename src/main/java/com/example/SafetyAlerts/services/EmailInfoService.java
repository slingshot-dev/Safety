package com.example.SafetyAlerts.services;

import com.example.SafetyAlerts.dao.IGetAll;
import com.example.SafetyAlerts.modeles.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailInfoService extends CommonsServices {


    public EmailInfoService(IGetAll<Person> personDAO, IGetAll<Firestation> firestationDAO, IGetAll<MedicalRecord> medicDA0) {
        super(personDAO, firestationDAO, medicDA0);
    }


    /**
     * @param city ; Parametre City de la personne
     * @return : Retour la liste des personnes avec leur Email.
     */

    public List<PersonInfos> getEmail(String city) {

        List<PersonInfos> resultEmail = new ArrayList<>();

        // Recuperation des Email dans Person et Ajout a l'Objet cible.
        getPersonAll().forEach(person -> {
            if (person.getCity().contentEquals(city)) {
                String firstname = person.getFirstName();
                String lastname = person.getLastName();

                resultEmail.add(getPersonFullInfos(firstname, lastname));
            }
        });

        return resultEmail;
    }


}

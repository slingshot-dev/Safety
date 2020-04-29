package com.example.SafetyAlerts.services;

import com.example.SafetyAlerts.dao.IGetAll;
import com.example.SafetyAlerts.modeles.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de creation de la liste EmailUrl.
 * Cette url retourne les adresses mail de tous les habitants de la ville.
 */

@Service
public class EmailInfoService extends CommonsServices {

    public EmailInfoService(IGetAll<Person> personDAO, IGetAll<Firestation> firestationDAO, IGetAll<MedicalRecord> medicDA0) {
        super(personDAO, firestationDAO, medicDA0);
    }

    /**
     *
     * @param city ; Parametre City de la personne
     * @return : Retour la liste des personnes avec leur Email.
     */

    public List<EmailUrl> getEmail(String city) {

        List<EmailUrl> resultEmail = new ArrayList<>();

        // Recuperation des Email dans Person et Ajout a l'Objet cible.
        getPersonAll().forEach(person -> {
            if (person.getCity().contentEquals(city)) {
                EmailUrl emailUrl = new EmailUrl();
                emailUrl.setFirstName(person.getFirstName());
                emailUrl.setLastName(person.getLastName());
                emailUrl.setEmail(person.getEmail());
                resultEmail.add(emailUrl);
            }
        });

        return resultEmail;
    }


}

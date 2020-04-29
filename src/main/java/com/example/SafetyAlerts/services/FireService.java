package com.example.SafetyAlerts.services;

import com.example.SafetyAlerts.dao.IGetAll;
import com.example.SafetyAlerts.modeles.*;
import com.example.SafetyAlerts.utils.GetAge;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de creation de la liste FirestationUrl.
 * Cette url retourne la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la caserne
 * de pompiers la desservant. La liste inclue le nom, le numéro de téléphone, l'âge et les antécédents
 * médicaux (médicaments, posologie et allergies) de chaque personne.
 */

@Service
public class FireService extends CommonsServices {
    private String StationNumber;

    public FireService(IGetAll<Person> personDAO, IGetAll<Firestation> firestationDAO, IGetAll<MedicalRecord> medicDA0) {
        super(personDAO, firestationDAO, medicDA0);
    }


    /**
     *
     * @param address : Parametre adresse d'une Firestation
     * @return : Retourne la Liste FireUrl
     */

    public List<FireUrl> getFire(String address) {

        List<FireUrl> result2 = new ArrayList<>();

        // Recuperation du numero de Firestation Correspondante a l'Adresse
        StationNumber = getFireAdress(address);

        // Recuperation des Personnes vivant a cette adresse
        getPersonAdress(address).forEach(person -> {
                String firstname = person.getFirstName();
                String lastname = person.getLastName();

                FireUrl fireUrl = new FireUrl();
                fireUrl.setFirstName(person.getFirstName());
                fireUrl.setLastName(person.getLastName());
                fireUrl.setPhone(person.getPhone());

            // Recuperation des Dossiers Medicaux des Personnes.
            resultPersonsMedic(firstname, lastname).forEach(person2 -> {
                        String birthDate = person2.getBirthdate();
                        String age = GetAge.getAge(birthDate);

                        fireUrl.setAge(age);
                        fireUrl.setAllergies(person2.getAllergies());
                        fireUrl.setMedics(person2.getMedications());
                        fireUrl.setFirestationNumber(StationNumber);

                        result2.add(fireUrl);
                });
        });

        return result2;
    }

}

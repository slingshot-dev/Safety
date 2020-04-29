package com.example.SafetyAlerts.services;

import com.example.SafetyAlerts.dao.IGetAll;
import com.example.SafetyAlerts.modeles.*;
import com.example.SafetyAlerts.utils.GetAge;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/** Classe de creation de la liste FirestationUrl.
 * Cette url retourne une liste des personnes couvertes par la caserne de pompiers correspondante.
 La liste inclue les informations spécifiques suivantes : prénom, nom, adresse, numéro de téléphone. De plus,
 elle fournie un décompte du nombre d'adultes et du nombre d'enfants (tout individu âgé de 18 ans ou
 moins) dans la zone desservie.
 *
 */

@Service
public class FirestationService extends CommonsServices {

    FirestationUrl firestationUrl = new FirestationUrl();

    public FirestationService(IGetAll<Person> personDAO, IGetAll<Firestation> firestationDAO, IGetAll<MedicalRecord> medicDA0) {
        super(personDAO, firestationDAO, medicDA0);
    }


    /**
     *
     * @param station : Parametre de numero de Station
     * @return : Retourne la liste FirestationUrl
     */

    public FirestationUrl getFirestation(String station) {

        ArrayList<PersonParFirestation> result2 = new ArrayList<>();

        AtomicInteger ageEnfant = new AtomicInteger();
        AtomicInteger ageAdult = new AtomicInteger();


        getFirestationAll().forEach(firestation -> {
            if (firestation.getStation().contentEquals(station)) {
                String address = firestation.getAddress();

                getPersonAll().forEach(person -> {
                    if (person.getAddress().contentEquals(address)) {
                        String firstname = person.getFirstName();
                        String lastname = person.getLastName();

                        PersonParFirestation personParFirestation = new PersonParFirestation();
                        personParFirestation.setFirstName(person.getFirstName());
                        personParFirestation.setLastName(person.getLastName());
                        personParFirestation.setAddress(person.getAddress());
                        personParFirestation.setPhone(person.getPhone());

                        result2.add(personParFirestation);
                        firestationUrl.setPersons(result2);

                        getMedicAll().forEach(person2 -> {
                            if (person2.getLastName().contentEquals(lastname) && person2.getFirstName().contentEquals(firstname)) {
                                String birthDate = person2.getBirthdate();
                                String age = GetAge.getAge(birthDate);

                                int ageInt = Integer.parseInt(age);
                                if (ageInt <= 18) {
                                    ageEnfant.getAndIncrement();
                                } else {
                                    ageAdult.getAndIncrement();
                                }
                                firestationUrl.setNbAdults(String.valueOf(ageAdult.get()));
                                firestationUrl.setNbEnfants(String.valueOf(ageEnfant.get()));
                            }
                        });
                    }
                });
            }
        });
        return firestationUrl;
    }


    /** Methode d'ajout d'une Firestation a la liste Firestations
     *
     * @param addFirestation : Parametres d'ajout d'une Firestation a la Liste Firestations
     */

    public void setAddFirestation(Firestation addFirestation) {


        // Ajout d'un objet complet(une firestation) a la Liste de Firestation.

        firestationSave(addFirestation);

    }

    /** Methode de modification d'une Firestation a la liste Firestations
     *
     * @param updateFirestation : Parametres de modification d'une Firestation a la Liste Firestations
     */

    public void setUpdateFirestation(Firestation updateFirestation) {


        String address = updateFirestation.getAddress();
        String station = updateFirestation.getStation();

        // Modification du numero d'une station en fonction de son adresse
        getFirestationAll().forEach(firestation -> {
            if (firestation.getAddress().contentEquals(address)) {
                int index = getFirestationAll().indexOf(firestation);

                // mise a jour de la firestation
                firestationUpdate(updateFirestation, index);

            }
        });

        // Modification de l'adresse d'une station en fonction de son numero
        getFirestationAll().forEach(firestation -> {
            if (firestation.getStation().contentEquals(station)) {
                int index = getFirestationAll().indexOf(firestation);

                // mise a jour de la firestation
                firestationUpdate(updateFirestation, index);

            }
        });

    }

    /** Methode de suppression d'une Firestation a la liste Firestations
     *
     * @param removeFirestation : Parametres de suppression d'une Firestation a la Liste Firestations
     */

    public void setRemoveFirestation(Firestation removeFirestation) {

        String address = removeFirestation.getAddress();
        String station = removeFirestation.getStation();

        // Suppression d'une firestation dans Firestation
        List<Firestation> filteredList = getFirestationAll().stream()
                .filter(fire1 -> (!fire1.getAddress()
                        .contentEquals(address) && !fire1.getStation()
                        .contentEquals(station)))
                .collect(Collectors.toList());

        firestationRemove(filteredList);

    }


}


package com.example.SafetyAlerts.services;

import com.example.SafetyAlerts.dao.IGetAll;
import com.example.SafetyAlerts.modeles.*;
import com.example.SafetyAlerts.utils.GetAge;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/** Classe de creation de la liste PersonUrl :
 * Cette url retourne le nom, l'adresse, l'âge, l'adresse mail et les antécédents médicaux (médicaments,
 * posologie, allergies) de chaque habitant. Si plusieurs personnes portent le même nom.
 */

@Service
public class PersonInfoService extends CommonsServices {

    MedicalRecord medicalRecord = new MedicalRecord();

    public PersonInfoService(IGetAll<Person> personDAO, IGetAll<Firestation> firestationDAO, IGetAll<MedicalRecord> medicDA0) {
        super(personDAO, firestationDAO, medicDA0);
    }

    /**
     * @param firstname : Parametre Prenom de la Personne issue du controller
     * @param lastname : Parametre Nom de la personne issue du controller
     * @return : Retourne la Liste PersonUrl
     */


    public List<PersonInfos> getPersonFindByName(String firstname, String lastname) {


        List<PersonInfos> result2 = new ArrayList<>();

                getPersonAll().forEach(person -> {
                    if (person.getLastName().contentEquals(lastname) ) {

                        String firstname2 = person.getFirstName();
                        String lastname2 = person.getLastName();

                        PersonInfos personInfos = new PersonInfos();
                        personInfos.setFirstName(person.getFirstName());
                        personInfos.setLastName(person.getLastName());
                        personInfos.setEmail(person.getEmail());

                        getMedicAll().forEach(person2 -> {
                            if (person2.getLastName().contentEquals(lastname2) && person2.getFirstName().contentEquals(firstname2)) {
                                String birthDate = person2.getBirthdate();
                                String age = GetAge.getAge(birthDate);

                                personInfos.setAllergies(person2.getAllergies());
                                personInfos.setMedics(person2.getMedications());
                                personInfos.setAge(age);

                                result2.add(personInfos);
                            }
                        });
                    }
                });
        return result2;
    }

    public void setAddPerson(Person addPerson) {

        Firestation firestation = new Firestation();

        // Ajout d'un objet complet(une personne) a la Liste de Person.
        personSave(addPerson);

        // Ajout des informations a Firestation en fonction de la Personne ajoutée
        String address = addPerson.getAddress();
        String station = "TbD";
        firestation.setAddress(address);
        firestation.setStation(station);
        firestationSave(firestation);

        // Ajout d'un dossier Medical vide en fonction de la Personne ajoutée
        medicalRecord.setFirstName(addPerson.getFirstName());
        medicalRecord.setLastName(addPerson.getLastName());
        medicalRecord.setBirthdate("TbD");
        medicalRecord.setMedications(null);
        medicalRecord.setAllergies(null);
        medicSave(medicalRecord);

    }

    /** Methode de mise a jour d'informations d'une Personne dans la liste de Personnes
     *
     * @param putPerson : parametres issues du controller. Informations d'une Personne a modifier
     */

    public void setUpdatePerson(Person putPerson) {

        List<Person> resultPerson = getPersonAll();

        String firstname = putPerson.getFirstName();
        String lastname = putPerson.getLastName();

        resultPerson.forEach(person -> {
            if (person.getLastName().contentEquals(lastname) && person.getFirstName().contentEquals(firstname)) {
                int index = resultPerson.indexOf(person);
                String address = resultPerson.get(index).getAddress();

                // mise a jour de la personne
                personUpdate(putPerson, index);

                // Ajout de Firestation si elle n'existe pas a cette adresse
                List<Firestation> filteredList = getFirestationAll().stream()
                        .filter(person2 -> person2.getAddress().contentEquals(address))
                        .collect(Collectors.toList());

                if (filteredList.isEmpty()) {
                    Firestation firestation = new Firestation();
                    firestation.setAddress(address);
                    firestation.setStation("TbD");
                    firestationSave(firestation);
                }
            }
        });

    }

    /** Methode de suppression d'une personne dans la liste de Personnes
     *
     * @param removePerson : parametres issues du controller. Personne a supprimer.
     */

    public void setRemovePerson(Person removePerson) {

        String firstname = removePerson.getFirstName();
        String lastname = removePerson.getLastName();

        // Suppression d'une personne dans Person
        List<Person> filteredList = getPersonAll().stream().filter(person4 -> !person4.getFirstName().contentEquals(firstname) && !person4.getFirstName().contentEquals(lastname)).collect(Collectors.toList());
        personRemove(filteredList);

        // Suppression des Records Medicaux de la personne supprimée de la List Person.
        List<MedicalRecord> filteredListMed = getMedicAll().stream().filter(person5 -> !person5.getFirstName().contentEquals(firstname) && !person5.getFirstName().contentEquals(lastname)).collect(Collectors.toList());
        medicRemove(filteredListMed);
    }
}


package com.example.SafetyAlerts.services;

import com.example.SafetyAlerts.dao.IGetAll2;
import com.example.SafetyAlerts.modeles.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetPerson {


    private final IGetAll2<Person> personDAO;
    private final IGetAll2<Firestation> firestationDAO;
    private final IGetAll2<MedicalRecord> medicDA0;

    MedicalRecord medicalRecord = new MedicalRecord();

    public SetPerson(IGetAll2<Person> personDAO, IGetAll2<Firestation> firestationDAO, IGetAll2<MedicalRecord> medicDA0) {
        this.personDAO = personDAO;
        this.firestationDAO = firestationDAO;
        this.medicDA0 = medicDA0;
    }

    public void setAddPerson(Person addPerson) {

        Firestation firestation = new Firestation();

        // Ajout d'un objet complet(une personne) a la Liste de Person.
        personDAO.save(addPerson);


        // Ajout des informations a Firestation en fonction de la Personne ajoutée
        String address = addPerson.getAddress();
        String station = "TbD";
        firestation.setAddress(address);
        firestation.setStation(station);
        firestationDAO.save(firestation);

        // Ajout d'un dossier Medical vide en fonction de la Personne ajoutée
        medicalRecord.setFirstName(addPerson.getFirstName());
        medicalRecord.setLastName(addPerson.getLastName());
        medicalRecord.setBirthdate("TbD");
        medicalRecord.setMedications(null);
        medicalRecord.setAllergies(null);
        medicDA0.save(medicalRecord);

    }

    public void setUpdatePerson(Person putPerson) {

        List<Person> resultPerson = personDAO.getAll();
        List<Firestation> resultFire = firestationDAO.getAll();
        String firstname = putPerson.getFirstName();
        String lastname = putPerson.getLastName();


        resultPerson.forEach(person -> {
            if (person.getLastName().contentEquals(lastname) && person.getFirstName().contentEquals(firstname)) {
                int index = resultPerson.indexOf(person);
                String address = resultPerson.get(index).getAddress();

                // mise a jour de la personne
                personDAO.update(putPerson, index);

                // Ajout de Firestation si elle n'existe pas a cette adresse
                List<Firestation> filteredList = resultFire.stream()
                        .filter(person2 -> person2.getAddress().contentEquals(address))
                        .collect(Collectors.toList());

                if (filteredList.isEmpty()) {
                    Firestation firestation = new Firestation();
                    firestation.setAddress(address);
                    firestation.setStation("TbD");
                    firestationDAO.save(firestation);
                }
            }
        });

    }


    public void setRemovePerson(Person removePerson) {

        List<Person> resultPerson = personDAO.getAll();
        List<MedicalRecord> resultMedic = medicDA0.getAll();

        String firstname = removePerson.getFirstName();
        String lastname = removePerson.getLastName();

        // Suppression d'une personne dans Person
        List<Person> filteredList = resultPerson.stream().filter(person4 -> !person4.getFirstName().contentEquals(firstname) && !person4.getFirstName().contentEquals(lastname)).collect(Collectors.toList());
        personDAO.delete(filteredList);


        // Suppression des Records Medicaux de la personne supprimée de la List Person.
        List<MedicalRecord> filteredListMed = resultMedic.stream().filter(person5 -> !person5.getFirstName().contentEquals(firstname) && !person5.getFirstName().contentEquals(lastname)).collect(Collectors.toList());
        medicDA0.delete(filteredListMed);

    }

}


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

public class SetMedic {

    IGetAll2<Person> personDAO = new PersonDAO();
    IGetAll2<MedicalRecord> medicDA0 = new MedicDA0();
    IGetAll2<Firestation> firestationDAO = new FirestationDAO();

    Person newPerson = new Person();

    public void setAddMedic(MedicalRecord addMedic) {


        // Ajout d'un objet complet(un dossier Medical) a la Liste de MedicalRecord.
        medicDA0.save(addMedic);

        // Ajout des informations a Personne en fonction du nouveau dossier Medical
        String firstname = addMedic.getFirstName();
        String lastname = addMedic.getLastName();
        newPerson.setFirstName(firstname);
        newPerson.setLastName(lastname);
        newPerson.setAddress("TbD");
        newPerson.setCity("TbD");
        newPerson.setZip(null);
        newPerson.setPhone("TbD");
        newPerson.setEmail("TbD");

        personDAO.save(newPerson);
    }

    public void setUpdateMedic(MedicalRecord UpdateMedic) {


        List<MedicalRecord> resultMedic = medicDA0.getAll();


        // Modification d'un dossier medical en fonction nom et prenom
        resultMedic.forEach(medic -> {
            if (medic.getFirstName().contentEquals(UpdateMedic.getFirstName())&&medic.getLastName().contentEquals(UpdateMedic.getLastName())) {
                int index = resultMedic.indexOf(medic);

                // mise a jour de MedicalRecord
                medicDA0.update(UpdateMedic, index);
            }
        });
    }

    public void setRemoveMedic(MedicalRecord removeMedic) {

        List<Person> resultPerson = personDAO.getAll();
        List<MedicalRecord> resultMedic = medicDA0.getAll();

        String firstname = removeMedic.getFirstName();
        String lastname = removeMedic.getLastName();

        // Suppression d'un dossier Medical dans MedicalRecord
        List<MedicalRecord> filteredListMedic = resultMedic.stream().filter(medic1 -> !medic1.getFirstName().contentEquals(firstname) && !medic1.getFirstName().contentEquals(lastname)).collect(Collectors.toList());
        medicDA0.delete(filteredListMedic);

        // Suppression de la personne dans Person si dossier Medical supprimé et si seulement si Personne existante a des informations vides.
        // Dans le cas contraire recreer dossier Medical vide

        List<Person> filteredListPerson = resultPerson.stream()
                .filter(person1 -> person1
                        .getFirstName().contentEquals(firstname)
                        && person1.getLastName().contentEquals(lastname)
                        && person1.getAddress().contentEquals("TbD")
                        && person1.getPhone().contentEquals("TbD")
                        && person1.getCity().contentEquals("TbD")
                        && person1.getEmail().contentEquals("TbD"))
                .collect(Collectors.toList());

        if (filteredListPerson.isEmpty()){

            // Ajout d'un dossier Medical vide en fonction de la Personne
            MedicalRecord medicalRecord = new MedicalRecord();

            medicalRecord.setFirstName(removeMedic.getFirstName());
            medicalRecord.setLastName(removeMedic.getLastName());
            medicalRecord.setBirthdate("TbD");
            medicalRecord.setMedications(null);
            medicalRecord.setAllergies(null);
            medicDA0.save(medicalRecord);
        }
        else {

            List<Person> filteredListPerson2 = resultPerson.stream()
                    .filter(person2 -> !person2.getFirstName().contentEquals(firstname) && !person2.getFirstName().contentEquals(lastname))
                    .collect(Collectors.toList());

            personDAO.delete(filteredListPerson2);

        }
    }


}

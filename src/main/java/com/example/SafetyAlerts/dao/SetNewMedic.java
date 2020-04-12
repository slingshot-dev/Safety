package com.example.SafetyAlerts.dao;

import com.example.SafetyAlerts.SafetyAlertsMapper;
import com.example.SafetyAlerts.modeles.Firestation;
import com.example.SafetyAlerts.modeles.MedicalRecord;
import com.example.SafetyAlerts.modeles.ObjectFromData;
import com.example.SafetyAlerts.modeles.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SetNewMedic implements ISetMedic {

    @Autowired
    SafetyAlertsMapper safetyAlertsMapper;

    @Override
    public void setAddMedic(MedicalRecord addMedic) {

        Person person = new Person();
        ObjectFromData objectFromDatas = SafetyAlertsMapper.read();

        // Ajout d'un objet complet(un dossier Medical) a la Liste de MedicalRecord.
        objectFromDatas.getMedicalrecords().add(addMedic);

        // Ajout des informations a Personne en fonction du nouveau dossier Medical
        String firstname = addMedic.getFirstName();
        String lastname = addMedic.getLastName();
        person.setFirstName(firstname);
        person.setLastName(lastname);
        person.setAddress("TbD");
        person.setCity("TbD");
        person.setZip(null);
        person.setPhone("TbD");
        person.setEmail("TbD");

        objectFromDatas.getPersons().add(person);

        // ecriture du fichier Json
        SafetyAlertsMapper.write(objectFromDatas);
    }

    @Override
    public void setUpdateMedic(MedicalRecord UpdateMedic) {

        ObjectFromData objectFromDatas = SafetyAlertsMapper.read();
        List<MedicalRecord> resultMedic = objectFromDatas.getMedicalrecords();


        // Modification du numero d'une station en fonction de son adresse
        resultMedic.forEach(medic -> {
            if (medic.getFirstName().contentEquals(UpdateMedic.getFirstName())&&medic.getLastName().contentEquals(UpdateMedic.getLastName())) {
                int index = resultMedic.indexOf(medic);

                // mise a jour de MdicalRecord
                resultMedic.get(index).setMedications(UpdateMedic.getMedications());
                resultMedic.get(index).setAllergies(UpdateMedic.getAllergies());
                resultMedic.get(index).setBirthdate(UpdateMedic.getBirthdate());

            }
        });

        // Ecriture du fichier Json avec les modifications

        SafetyAlertsMapper.write(objectFromDatas);


    }
    @Override
    public void setRemoveMedic(MedicalRecord removeMedic) {

        ObjectFromData objectFromDatas = SafetyAlertsMapper.read();
        List<Person> resultPerson = objectFromDatas.getPersons();
        MedicalRecord medicalRecord = new MedicalRecord();
        List<MedicalRecord> resultMedic = objectFromDatas.getMedicalrecords();

        String firstname = removeMedic.getFirstName();
        String lastname = removeMedic.getLastName();

        // Suppression d'un dossier Medical dans MedicalRecord
        List<MedicalRecord> filteredListMedic = resultMedic.stream().filter(medic1 -> !medic1.getFirstName().contentEquals(firstname) && !medic1.getFirstName().contentEquals(lastname)).collect(Collectors.toList());
        objectFromDatas.setMedicalrecords(filteredListMedic);

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
            medicalRecord.setFirstName(removeMedic.getFirstName());
            medicalRecord.setLastName(removeMedic.getLastName());
            medicalRecord.setBirthdate("TbD");
            medicalRecord.setMedications(null);
            medicalRecord.setAllergies(null);
            objectFromDatas.getMedicalrecords().add(medicalRecord);

        }
        else {

            List<Person> filteredListPerson2 = resultPerson.stream()
                    .filter(person2 -> !person2.getFirstName().contentEquals(firstname) && !person2.getFirstName().contentEquals(lastname))
                    .collect(Collectors.toList());
            objectFromDatas.setPersons(filteredListPerson2);

        }

        // ecriture du fichier Json
        SafetyAlertsMapper.write(objectFromDatas);

    }

}

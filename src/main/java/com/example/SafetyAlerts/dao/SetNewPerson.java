package com.example.SafetyAlerts.dao;

import com.example.SafetyAlerts.SafetyAlertsMapper;
import com.example.SafetyAlerts.modeles.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SetNewPerson implements ISetNewPerson{

    @Autowired
    SafetyAlertsMapper safetyAlertsMapper;

    @Override
    public void setAddPerson(Person addPerson) {

        Firestation firestation = new Firestation();
        MedicalRecord medicalRecord = new MedicalRecord();
        ObjectFromData objectFromDatas = SafetyAlertsMapper.read();

        // Ajout d'un objet complet(une personne) a la Liste de Person.
        objectFromDatas.getPersons().add(addPerson);

        // Ajout des informations a Firestation en fonction de la Personne ajoutée
        String address = addPerson.getAddress();
        String station = "TbD";
        firestation.setAddress(address);
        firestation.setStation(station);
        objectFromDatas.getFirestations().add(firestation);

        // Ajout des Recors Medicaux en fonction al Personne ajoutée
        medicalRecord.setFirstName(addPerson.getFirstName());
        medicalRecord.setLastName(addPerson.getLastName());
        medicalRecord.setBirthdate("TbD");
        medicalRecord.setMedications(null);
        medicalRecord.setAllergies(null);
        objectFromDatas.getMedicalrecords().add(medicalRecord);

        // ecriture du fichier Json
        SafetyAlertsMapper.write(objectFromDatas);
    }

    @Override
    public void setUpdatePerson(Person putPerson) {
        ObjectFromData objectFromDatas = SafetyAlertsMapper.read();
        List<Person> resultPerson = objectFromDatas.getPersons();
        List<Firestation> resultFire = objectFromDatas.getFirestations();
        String firstname = putPerson.getFirstName();
        String lastname = putPerson.getLastName();

        resultPerson.forEach(person -> {
            if (person.getLastName().contentEquals(lastname) && person.getFirstName().contentEquals(firstname)) {
                int index = resultPerson.indexOf(person);
                String address = resultPerson.get(index).getAddress();

                // mise a jour de la personne
                resultPerson.get(index).setAddress(putPerson.getAddress());
                resultPerson.get(index).setCity(putPerson.getCity());
                resultPerson.get(index).setZip(putPerson.getZip());
                resultPerson.get(index).setPhone(putPerson.getPhone());
                resultPerson.get(index).setEmail(putPerson.getEmail());

                // mise a jour de firestation en fonction de l'adresse de la personne
                resultFire.forEach(person2 ->{
                    if (person2.getAddress().contentEquals(address)){
                        int indexFire = resultFire.indexOf((person2));
                        resultFire.get(indexFire).setAddress(putPerson.getAddress());
                    }
                });

                // mise a jour de l'objet global + ecriture du json
                objectFromDatas.setPersons(resultPerson);
                objectFromDatas.setFirestations(resultFire);

                SafetyAlertsMapper.write(objectFromDatas);
            }
        });

    }

    @Override
    public void setRemovePerson(Person removePerson) {

        ObjectFromData objectFromDatas = SafetyAlertsMapper.read();
        List<Person> resultPerson = objectFromDatas.getPersons();
        List<MedicalRecord> resultMedic = objectFromDatas.getMedicalrecords();

        String firstname = removePerson.getFirstName();
        String lastname = removePerson.getLastName();

        // Suppression d'une personne dans Person
        List<Person> filteredList = resultPerson.stream().filter(person4 -> !person4.getFirstName().contentEquals(firstname) && !person4.getFirstName().contentEquals(lastname)).collect(Collectors.toList());
        objectFromDatas.setPersons(filteredList);

        // Suppression des Records Medicaux de la personne supprimée de la List Person.
        List<MedicalRecord> filteredListMed = resultMedic.stream().filter(person5 -> !person5.getFirstName().contentEquals(firstname) && !person5.getFirstName().contentEquals(lastname)).collect(Collectors.toList());
        objectFromDatas.setMedicalrecords(filteredListMed);

        // ecriture du fichier Json
        SafetyAlertsMapper.write(objectFromDatas);

    }

}

package com.example.SafetyAlerts.dao;

import com.example.SafetyAlerts.SafetyAlertsMapper;
import com.example.SafetyAlerts.modeles.*;
import com.example.SafetyAlerts.utils.GetAge;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetFire implements IGetFire {

    /**
     * This URL return from Adress, the folowing informations :
     * FirstName, Lastname, phone Number, age, Medicals records, Station Number.
     *
     * @return
     */

    @Override
    public FireList getFire(String address) {

        List<Person> result = getPersonAll();
        List<Firestation> resultFire = getFireAll();
        ArrayList<FireUrl> result2 = new ArrayList<>();

        FireList fireList = new FireList();


        resultFire.forEach(firestation -> {
            if (firestation.getAddress().contentEquals(address)) {
                String numStation = firestation.getStation();

                result.forEach(person -> {
                    if (person.getAddress().contentEquals(address)) {
                        String firstname = person.getFirstName();
                        String lastname = person.getLastName();

                        FireUrl fireUrl = new FireUrl();
                        fireUrl.setFirstName(person.getFirstName());
                        fireUrl.setLastName(person.getLastName());
                        fireUrl.setPhone(person.getPhone());

                        getMedAll().forEach(person2 -> {
                            if (person2.getLastName().contentEquals(lastname) && person2.getFirstName().contentEquals(firstname)) {
                                String birthDate = person2.getBirthdate();
                                String age = GetAge.getAge(birthDate);

                                fireUrl.setAge(age);
                                fireUrl.setAllergies(person2.getAllergies());
                                fireUrl.setMedics(person2.getMedications());
                                fireUrl.setFirestationNumber(numStation);

                                result2.add(fireUrl);
                            }
                        });
                    }
                });
            }
        });
        fireList.setFireUrls(result2);
        return fireList;
    }

    @Override
    public List<Person> getPersonAll() {
        ObjectFromData objectsFromData = SafetyAlertsMapper.read();
        return objectsFromData.getPersons();
    }

    @Override
    public List<MedicalRecord> getMedAll(){
        ObjectFromData objectsFromData = SafetyAlertsMapper.read();
        return objectsFromData.getMedicalrecords();
    }

    @Override
    public List<Firestation> getFireAll(){
        ObjectFromData objectsFromData = SafetyAlertsMapper.read();
        return objectsFromData.getFirestations();
    }



}

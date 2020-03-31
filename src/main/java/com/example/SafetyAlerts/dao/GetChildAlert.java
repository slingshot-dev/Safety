package com.example.SafetyAlerts.dao;

import com.example.SafetyAlerts.SafetyAlertsMapper;
import com.example.SafetyAlerts.modeles.Firestation;
import com.example.SafetyAlerts.modeles.MedicalRecord;
import com.example.SafetyAlerts.modeles.ObjectFromData;
import com.example.SafetyAlerts.modeles.Person;
import com.example.SafetyAlerts.utils.GetAge;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetChildAlert implements IGetChildAlert {

    /**
     * This URL return from Address, the folowing informations :
     * enfant de 18 ans ou moins, Firstname, Lastname, age, autres mebres du foyer.
     *
     * @return
     */

    @Override
    public ArrayList<String> getChildAlert(String address) {
        List<Person> result = getPersonAll();
        List<Firestation> resultFire = getFireAll();
        List<MedicalRecord> resultMedAll = getMedAll();
        ArrayList<String> result2 = new ArrayList<>();


                result.forEach(person -> {
                    if (person.getAddress().contentEquals(address)) {
                        String firstname = person.getFirstName();
                        String lastname = person.getLastName();

                        getMedAll().forEach(person2 -> {
                            if (person2.getLastName().contentEquals(lastname) && person2.getFirstName().contentEquals(firstname)) {
                                String birthDate = person2.getBirthdate();
                                String age = GetAge.getAge(birthDate);
                                int ageInt = Integer.parseInt(age);
                                if (ageInt <= 18 ) {
                                    result2.add(person.getFirstName());
                                    result2.add(person.getLastName());
                                    result2.add(age);

                                    result.forEach(person3 -> {
                                        if (person3.getLastName().contentEquals(lastname)) {
                                            result2.add(person3.getFirstName());
                                            result2.add(person3.getLastName());
                                        }
                                    });

                                }



                              }
                        });
                    }
                });

        return result2;
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


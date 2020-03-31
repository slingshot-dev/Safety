package com.example.SafetyAlerts.dao;

import com.example.SafetyAlerts.SafetyAlertsMapper;
import com.example.SafetyAlerts.modeles.Firestation;
import com.example.SafetyAlerts.modeles.MedicalRecord;
import com.example.SafetyAlerts.modeles.ObjectFromData;
import com.example.SafetyAlerts.modeles.Person;
import com.example.SafetyAlerts.utils.GetAge;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class GetFlood implements IGetFlood {


    /**
     * This URL return from FireStation, the folowing informations :
     * Lastname, phone Number, age, Email, Medicals records.
     *
     * @return
     */

    @Override
    public ArrayList<String> getFlood(String station) {

        List<Person> result = getPersonAll();
        List<Firestation> resultFire = getFireAll();
        ArrayList<String> result2 = new ArrayList<>();

        resultFire.forEach(firestation -> {
            if (firestation.getStation().contentEquals(station)) {
                String adress = firestation.getAddress();

                result.forEach(person -> {
                    if (person.getAddress().contentEquals(adress)) {
                        String firstname = person.getFirstName();
                        String lastname = person.getLastName();
                        result2.add(person.getFirstName());
                        result2.add(person.getLastName());
                        result2.add(person.getAddress());
                        result2.add(person.getPhone());

                        getMedAll().forEach(person2 -> {
                            if (person2.getLastName().contentEquals(lastname) && person2.getFirstName().contentEquals(firstname)) {
                                String birthDate = person2.getBirthdate();
                                String age = GetAge.getAge(birthDate);
                                result2.add(age);
                                result2.add(person2.getMedications().toString());
                                result2.add(person2.getAllergies().toString());
                            }
                        });
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

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
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class GetFirestation implements IGetFirestation {

    /**
     * This URL return from Station number, the folowing informations :
     * FirstName, Lastname, address, phone Number, age, Medicals records, Station Number.
     * A count of adults en Childrens in this area
     *
     * @return
     */

    @Override
    public ArrayList<String> getFirestation(String station) {

        List<Person> result = getPersonAll();
        List<Firestation> resultFire = getFireAll();
        ArrayList<String> result2 = new ArrayList<>();
        AtomicInteger ageAdult = new AtomicInteger();
        AtomicInteger ageEnfant = new AtomicInteger();

        resultFire.forEach(firestation -> {
            if (firestation.getStation().contentEquals(station)) {
                String address = firestation.getAddress();

                result.forEach(person -> {
                    if (person.getAddress().contentEquals(address)) {
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
                                int ageInt = Integer.parseInt(age);
                                if (ageInt <= 18){
                                    ageEnfant.getAndIncrement();
                                } else {
                                    ageAdult.getAndIncrement();
                                }

                            }
                        });
                    }
                });
            }
        });
        System.out.println(ageAdult +" "+ageEnfant);
        result2.add("Nombre d'adultes : "+ageAdult);
        result2.add("Nombre d'enfants :"+ageEnfant);
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

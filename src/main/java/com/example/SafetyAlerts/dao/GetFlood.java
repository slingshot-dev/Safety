package com.example.SafetyAlerts.dao;

import com.example.SafetyAlerts.SafetyAlertsMapper;
import com.example.SafetyAlerts.modeles.*;
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
    public FloodList getFlood(String station) {

        List<Person> result = getPersonAll();
        List<Firestation> resultFire = getFireAll();
        ArrayList<FloodUrl> result2 = new ArrayList<>();

        FloodList floodList = new FloodList();


        resultFire.forEach(firestation -> {
            if (firestation.getStation().contentEquals(station)) {
                String adress = firestation.getAddress();
                String numStation = firestation.getStation();

                result.forEach(person -> {
                    if (person.getAddress().contentEquals(adress)) {
                        String firstname = person.getFirstName();
                        String lastname = person.getLastName();

                        FloodUrl floodUrl = new FloodUrl();
                        floodUrl.setFirstName(person.getFirstName());
                        floodUrl.setLastName(person.getLastName());
                        floodUrl.setAddress(person.getAddress());
                        floodUrl.setPhone(person.getPhone());
                        floodUrl.setFirestationNumber(numStation);

/*                        result2.add(person.getFirstName());
                        result2.add(person.getLastName());
                        result2.add(person.getAddress());
                        result2.add(person.getPhone());*/

                        getMedAll().forEach(person2 -> {
                            if (person2.getLastName().contentEquals(lastname) && person2.getFirstName().contentEquals(firstname)) {
                                String birthDate = person2.getBirthdate();
                                String age = GetAge.getAge(birthDate);

                                floodUrl.setAge(age);
                                floodUrl.setMedics(person2.getMedications());
                                floodUrl.setAllergies(person2.getAllergies());

                                result2.add(floodUrl);

/*                                result2.add(person2.getMedications().toString());
                                result2.add(person2.getAllergies().toString());*/
                            }
                        });
                    }
                });
            }
        });
        floodList.setFloodUrls((result2));
        return floodList;
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

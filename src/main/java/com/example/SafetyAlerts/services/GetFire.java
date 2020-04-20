package com.example.SafetyAlerts.services;

import com.example.SafetyAlerts.dao.IGetAll2;
import com.example.SafetyAlerts.modeles.*;
import com.example.SafetyAlerts.utils.GetAge;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetFire {

    /**
     * This URL return from Adress, the folowing informations :
     * FirstName, Lastname, phone Number, age, Medicals records, Station Number.
     *
     * @return
     */

    private final IGetAll2<Person> personDAO;
    private final IGetAll2<Firestation> firestationDAO;
    private final IGetAll2<MedicalRecord> medicDA0;

    public GetFire(IGetAll2<Person> personDAO, IGetAll2<Firestation> firestationDAO, IGetAll2<MedicalRecord> medicDA0) {
        this.personDAO = personDAO;
        this.firestationDAO = firestationDAO;
        this.medicDA0 = medicDA0;
    }


    public List<FireUrl> getFire(String address) {

        List<Person> result = personDAO.getAll();
        List<Firestation> resultFire = firestationDAO.getAll();
        List<MedicalRecord> resultMedic = medicDA0.getAll();
        List<FireUrl> result2 = new ArrayList<>();


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

                        resultMedic.forEach(person2 -> {
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
        return result2;
    }

}

package com.example.SafetyAlerts.services;

import com.example.SafetyAlerts.dao.IGetAll2;
import com.example.SafetyAlerts.modeles.*;
import com.example.SafetyAlerts.utils.GetAge;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/** Classe de creation de la liste FirestationUrl. This URL return from Station number, the folowing informations :
 * FirstName, Lastname, address, phone Number, age, Medicals records, Station Number.
 * A count of adults en Childrens in this area
 */

@Service
public class GetFirestation {

    private final IGetAll2<Person> personDAO;
    private final IGetAll2<Firestation> firestationDAO;
    private final IGetAll2<MedicalRecord> medicDA0;

    FirestationUrl firestationUrl = new FirestationUrl();

    public GetFirestation(IGetAll2<Person> personDAO, IGetAll2<Firestation> firestationDAO, IGetAll2<MedicalRecord> medicDA0) {
        this.personDAO = personDAO;
        this.firestationDAO = firestationDAO;
        this.medicDA0 = medicDA0;
    }

    /**
     *
     * @param station : Parametre de numero de Station
     * @return : Retourne la liste FirestationUrl
     */

    public FirestationUrl getFirestation(String station) {

        List<Person> result = personDAO.getAll();
        List<Firestation> resultFire = firestationDAO.getAll();
        List<MedicalRecord> resultMedic = medicDA0.getAll();
        ArrayList<PersonParFirestation> result2 = new ArrayList<>();

        AtomicInteger ageEnfant = new AtomicInteger();
        AtomicInteger ageAdult = new AtomicInteger();


        resultFire.forEach(firestation -> {
            if (firestation.getStation().contentEquals(station)) {
                String address = firestation.getAddress();

                result.forEach(person -> {
                    if (person.getAddress().contentEquals(address)) {
                        String firstname = person.getFirstName();
                        String lastname = person.getLastName();

                        PersonParFirestation personParFirestation = new PersonParFirestation();

                        personParFirestation.setFirstName(person.getFirstName());
                        personParFirestation.setLastName(person.getLastName());
                        personParFirestation.setAddress(person.getAddress());
                        personParFirestation.setPhone(person.getPhone());

                        result2.add(personParFirestation);
                        firestationUrl.setPersons(result2);

                        resultMedic.forEach(person2 -> {
                            if (person2.getLastName().contentEquals(lastname) && person2.getFirstName().contentEquals(firstname)) {
                                String birthDate = person2.getBirthdate();
                                String age = GetAge.getAge(birthDate);

                                int ageInt = Integer.parseInt(age);
                                if (ageInt <= 18) {
                                    ageEnfant.getAndIncrement();
                                } else {
                                    ageAdult.getAndIncrement();
                                }
                                firestationUrl.setNbAdults(String.valueOf(ageAdult.get()));
                                firestationUrl.setNbEnfants(String.valueOf(ageEnfant.get()));
                            }
                        });
                    }
                });
            }
        });
        return firestationUrl;
    }
}


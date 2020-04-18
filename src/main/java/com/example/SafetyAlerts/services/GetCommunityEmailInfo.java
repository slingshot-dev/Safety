package com.example.SafetyAlerts.services;

import com.example.SafetyAlerts.dao.IGetAll2;
import com.example.SafetyAlerts.dao.impl.FirestationDAO;
import com.example.SafetyAlerts.dao.impl.MedicDA0;
import com.example.SafetyAlerts.dao.impl.PersonDAO;
import com.example.SafetyAlerts.modeles.*;

import java.util.ArrayList;
import java.util.List;

public class GetCommunityEmailInfo {


    IGetAll2<Person> personDAO = new PersonDAO();
    IGetAll2<MedicalRecord> medicDA02 = new MedicDA0();
    IGetAll2<Firestation> firestationDAO = new FirestationDAO();

    public List<Firestation> getAll() {

        return firestationDAO.getAll();
    }


    public List<EmailUrl> getEmail(String city) {

    List<EmailUrl> resultEmail = new ArrayList<>();

    personDAO.getAll().forEach(person -> {
        if (person.getCity().contentEquals(city)) {
            EmailUrl emailUrl = new EmailUrl();
            emailUrl.setFirstName(person.getFirstName());
            emailUrl.setLastName(person.getLastName());
            emailUrl.setEmail(person.getEmail());
            resultEmail.add(emailUrl);
        }
    });

        return resultEmail;
}



}

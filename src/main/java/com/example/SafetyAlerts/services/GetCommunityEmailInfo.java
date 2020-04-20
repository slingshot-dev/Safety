package com.example.SafetyAlerts.services;

import com.example.SafetyAlerts.dao.IGetAll2;
import com.example.SafetyAlerts.modeles.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetCommunityEmailInfo {

    private final IGetAll2<Person> personDAO;
    private final IGetAll2<Firestation> firestationDAO;

    public GetCommunityEmailInfo(IGetAll2<Person> personDAO, IGetAll2<Firestation> firestationDAO) {
        this.personDAO = personDAO;
        this.firestationDAO = firestationDAO;
    }


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

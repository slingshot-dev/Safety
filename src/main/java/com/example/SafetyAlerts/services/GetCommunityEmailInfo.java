package com.example.SafetyAlerts.services;

import com.example.SafetyAlerts.dao.IGetAll2;
import com.example.SafetyAlerts.modeles.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de creation de la liste EmailUrl.
 * This URL return from City, the folowing informations :
 * FirstName, Lastname, Email.
 */

@Service
public class GetCommunityEmailInfo {

    private final IGetAll2<Person> personDAO;

    public GetCommunityEmailInfo(IGetAll2<Person> personDAO) {
        this.personDAO = personDAO;
    }

    /**
     *
     * @param city ; Parametre City de la personne
     * @return : Retour la liste des personnes avec leur Email.
     */

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

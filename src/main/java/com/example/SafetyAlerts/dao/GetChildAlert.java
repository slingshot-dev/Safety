package com.example.SafetyAlerts.dao;

import com.example.SafetyAlerts.modeles.*;
import com.example.SafetyAlerts.utils.GetAge;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetChildAlert extends GetAll {


    public ArrayList<ChildAlertUrl> getChildAlert(String address) {
        List<Person> result = getPersonAll();
        List<MedicalRecord> resultMedic = getMedAll();
        ArrayList<ChildAlertUrl> result3 = new ArrayList<>();


        // Liste de personnes a l'adresse demandée
        List<Person> resultPersonAddress = result.stream().filter(child1 -> (child1.getAddress().contentEquals(address))).collect(Collectors.toList());

        // pour chaque personne recuperation du prenom et nom
        resultPersonAddress.forEach(person -> {
            if (person.getAddress().contentEquals(address)) {
                String firstname = person.getFirstName();
                String lastname = person.getLastName();

                // extraction de la personne dans Medical Record et verification de l'age
                List<MedicalRecord> resultPersonByName = resultMedic.stream()
                        .filter(person1 ->
                                (person1.getFirstName().contentEquals(firstname) &&
                                        person1.getLastName().contentEquals(lastname)))
                        .collect(Collectors.toList());
                String birthDate = resultPersonByName.get(0).getBirthdate();
                String age = GetAge.getAge(birthDate);
                int ageInt = Integer.parseInt(age);

                // Si 18 ans ou moins on ajoute l'enfant
                if (ageInt <= 18) {

                    ChildAlertUrl childAlertUrl = new ChildAlertUrl();
                    childAlertUrl.setFirstName(resultPersonByName.get(0).getFirstName());
                    childAlertUrl.setLastName(resultPersonByName.get(0).getLastName());
                    childAlertUrl.setAge(age);

                    // recuperation de la liste initale que l'on ajoute au foyer sans l'enfant.
                    List<Person> resultPersonParFoyer = resultPersonAddress.stream().filter(adults -> (adults.getLastName().contentEquals(lastname) && !adults.getFirstName().contentEquals(firstname))).collect(Collectors.toList());
                    childAlertUrl.setPersonParFoyers(resultPersonParFoyer);

                    result3.add(childAlertUrl);
                }
            }
        });
        return result3;
    }
}



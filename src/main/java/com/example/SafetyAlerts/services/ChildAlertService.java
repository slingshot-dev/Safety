package com.example.SafetyAlerts.services;

import com.example.SafetyAlerts.dao.IGetAll;
import com.example.SafetyAlerts.modeles.*;
import com.example.SafetyAlerts.utils.GetAge;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe de creation de la liste ChildAlertUrl.
 * Cette url retourne une liste d'enfants (tout individu âgé de 18 ans ou moins) habitant à cette adresse.
 * La liste comprend le prénom et le nom de famille de chaque enfant, son âge et une liste des autres
 * membres du foyer. S'il n'y a pas d'enfant, cette url renvoie une chaîne vide.
 */

@Service
public class ChildAlertService extends CommonsServices {

    private String Age;
    public ChildAlertService(IGetAll<Person> personDAO, IGetAll<Firestation> firestationDAO, IGetAll<MedicalRecord> medicDA0) {
        super(personDAO, firestationDAO, medicDA0);
    }

    /**
     *
     * @param address : Adress de Personnes
     * @return : Retourne La liste ChildAlertUrl.
     */

    public ArrayList<ChildAlertUrl> getChildAlert(String address) {
        List<Person> result = getPersonAll();
        ArrayList<ChildAlertUrl> result3 = new ArrayList<>();
        ArrayList<Child> resultChild = new ArrayList<>();


        // Liste de personnes a l'adresse demandée
        List<Person> resultPersonAddress = result.stream().filter(child1 -> (child1.getAddress().contentEquals(address))).collect(Collectors.toList());

        // Recuperation du dossier Medical de chaque personne
        resultPersonAddress.forEach(person -> {
              Age = GetAge.getAge(getBirthdate(person.getFirstName(), person.getLastName()).get(0).getBirthdate());
            int ageInt = Integer.parseInt(Age);
            if (ageInt <= 18) {
                Child child = new Child();
                ChildAlertUrl childAlertUrl = new ChildAlertUrl();

                // Vérification de l'age et Set et ajout de l'enfant si age correspond
                child.setFirstName(person.getFirstName());
                child.setLastName(person.getLastName());
                child.setAge(Age);
                resultChild.add(child);

                // Ajout des autres personnes du foyer.
                List<Person> resultPersonParFoyer = resultPersonAddress.stream().filter(adults -> (adults.getLastName().contentEquals(person.getLastName()) && !adults.getFirstName().contentEquals(person.getFirstName()))).collect(Collectors.toList());
                childAlertUrl.setPersonFoyer(resultPersonParFoyer);
                childAlertUrl.setEnfantFoyer(resultChild);

                result3.add(childAlertUrl);
            }
    });
        return removeDoublon(result3);
    }

}



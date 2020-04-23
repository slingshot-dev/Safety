package com.example.SafetyAlerts.services;

import com.example.SafetyAlerts.dao.IGetAll2;
import com.example.SafetyAlerts.modeles.*;
import com.example.SafetyAlerts.utils.GetAge;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe de creation de la liste ChildAlertUrl.
 * This URL return from Address, the folowing informations :
 * Childs Informations and personns from the same Adress.
 * It count alse numbers of Adults and Childs
 */

@Service
public class GetChildAlert {

    private final IGetAll2<MedicalRecord> medicDA0;
    private final IGetAll2<Person> personDAO;
    private String Age;

    public GetChildAlert(IGetAll2<Person> personDAO, IGetAll2<MedicalRecord> medicDA0) {
        this.personDAO = personDAO;
        this.medicDA0 = medicDA0;
    }

    /**
     *
     * @param address : Adress de Personnes
     * @return : Retourne La liste ChildAlertUrl.
     */

    public ArrayList<ChildAlertUrl> getChildAlert(String address) {
        List<Person> result = personDAO.getAll();
        ArrayList<ChildAlertUrl> result3 = new ArrayList<>();
        ArrayList<Child> resultChild = new ArrayList<>();


        // Liste de personnes a l'adresse demandée
        List<Person> resultPersonAddress = result.stream().filter(child1 -> (child1.getAddress().contentEquals(address))).collect(Collectors.toList());

        resultPersonAddress.forEach(person -> {
              Age = GetAge.getAge(getBirthdate(person.getFirstName(), person.getLastName()).get(0).getBirthdate());
            int ageInt = Integer.parseInt(Age);
            if (ageInt <= 18) {
                Child child = new Child();
                ChildAlertUrl childAlertUrl = new ChildAlertUrl();

                child.setFirstName(person.getFirstName());
                child.setLastName(person.getLastName());
                child.setAge(Age);
                resultChild.add(child);

                List<Person> resultPersonParFoyer = resultPersonAddress.stream().filter(adults -> (adults.getLastName().contentEquals(person.getLastName()) && !adults.getFirstName().contentEquals(person.getFirstName()))).collect(Collectors.toList());
                childAlertUrl.setPersonFoyer(resultPersonParFoyer);
                childAlertUrl.setEnfantFoyer(resultChild);

                result3.add(childAlertUrl);
            }
    });
        return removeDoublon(result3);
    }




    public List<MedicalRecord> getBirthdate(String firstName, String lasName) {
        List<MedicalRecord> resultMedic = medicDA0.getAll();
        return resultMedic.stream().filter(medic -> (medic.getFirstName().contentEquals(firstName) && medic.getLastName().contentEquals(lasName))).collect(Collectors.toList());
    }

    public ArrayList<ChildAlertUrl> removeDoublon(ArrayList<ChildAlertUrl> List){
        while (List.size()>1){
            List.remove(1);
        }
        return List;
    }

}



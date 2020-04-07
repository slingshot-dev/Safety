package com.example.SafetyAlerts.dao;

import com.example.SafetyAlerts.SafetyAlertsMapper;
import com.example.SafetyAlerts.modeles.*;
import com.example.SafetyAlerts.utils.GetAge;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetChildAlert extends GetAll implements IGetChildAlert {

    /**
     * This URL return from Address, the folowing informations :
     * enfant de 18 ans ou moins, Firstname, Lastname, age, autres mebres du foyer.
     *
     * @return
     */

    @Override
    public ChildAlertList getChildAlert(String address) {
        List<Person> result = getPersonAll();
        ArrayList<String> result2 = new ArrayList<>();
        ArrayList<ChildAlertUrl> result3 = new ArrayList<>();

        ChildAlertList childAlertList = new ChildAlertList();


        result.forEach(person -> {
            if (person.getAddress().contentEquals(address)) {
                String firstname = person.getFirstName();
                String lastname = person.getLastName();

                getMedAll().forEach(person2 -> {
                    if (person2.getLastName().contentEquals(lastname) && person2.getFirstName().contentEquals(firstname)) {
                        String birthDate = person2.getBirthdate();
                        String age = GetAge.getAge(birthDate);
                        int ageInt = Integer.parseInt(age);
                        if (ageInt <= 18) {

                            ChildAlertUrl childAlertUrl = new ChildAlertUrl();
                            childAlertUrl.setFirstName(person.getFirstName());
                            childAlertUrl.setLastName(person.getLastName());
                            childAlertUrl.setAge(age);

                            result.forEach(person3 -> {
                                if (person3.getLastName().contentEquals(lastname)) {
                                    result2.add(person3.getFirstName());
                                    result2.add(person3.getLastName());

                                    childAlertUrl.setPersonParFoyer(result2);
                                }
                            });
                            result3.add(childAlertUrl);
                        }
                    }
                });
            }
        });
        childAlertList.setChildAlertUrlArrayList(result3);
        return childAlertList;
    }

}


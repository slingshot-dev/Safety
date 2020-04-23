package com.example.SafetyAlerts.dao.impl;

import com.example.SafetyAlerts.dao.IGetAll2;
import com.example.SafetyAlerts.modeles.ObjectFromData;
import com.example.SafetyAlerts.modeles.Person;
import com.example.SafetyAlerts.utils.SafetyAlertsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;


@Repository("CPersonDAO")
public class PersonDAO implements IGetAll2<Person> {

    private List<Person> persons = new ArrayList<>();

    public PersonDAO() {
        persons.add(new Person());
    }

    @Autowired
    SafetyAlertsMapper safetyAlertsMapper;


    @Override
    public List<Person> getAll() {

        ObjectFromData objectsFromData = safetyAlertsMapper.read();
        return objectsFromData.getPersons();
    }

    @Override
    public void save(Person person) {

        ObjectFromData objectsFromData = safetyAlertsMapper.read();
        objectsFromData.getPersons().add(person);
        SafetyAlertsMapper.write(objectsFromData);
    }

    @Override
    public void update(Person person, int index) {

        ObjectFromData objectsFromData = safetyAlertsMapper.read();
        persons = objectsFromData.getPersons();
        persons.get(index).setFirstName(person.getFirstName());
        persons.get(index).setLastName(person.getLastName());
        persons.get(index).setAddress(person.getAddress());
        persons.get(index).setCity(person.getCity());
        persons.get(index).setZip(person.getZip());
        persons.get(index).setPhone(person.getPhone());
        persons.get(index).setEmail(person.getEmail());

        objectsFromData.setPersons(persons);
        SafetyAlertsMapper.write(objectsFromData);

    }

    @Override
    public void delete(List<Person> person) {

        ObjectFromData objectsFromData = safetyAlertsMapper.read();
        objectsFromData.setPersons(person);
        SafetyAlertsMapper.write(objectsFromData);
    }
}

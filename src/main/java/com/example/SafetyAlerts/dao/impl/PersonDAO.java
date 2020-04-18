package com.example.SafetyAlerts.dao.impl;

import com.example.SafetyAlerts.dao.IGetAll2;
import com.example.SafetyAlerts.modeles.ObjectFromData;
import com.example.SafetyAlerts.modeles.Person;
import com.example.SafetyAlerts.utils.SafetyAlertsMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PersonDAO implements IGetAll2<Person> {

    private List<Person> persons = new ArrayList<>();

/*    public PersonDAO() {
        persons.add(new Person());

    }*/


    @Override
    public Optional<Person> get(long id) {
        return Optional.ofNullable(persons.get((int) id));
    }

    @Override
    public List<Person> getAll() {
        ObjectFromData objectsFromData = SafetyAlertsMapper.read();
        persons =  objectsFromData.getPersons();
        return persons;
    }

    @Override
    public void save(Person person) {
        ObjectFromData objectsFromData = SafetyAlertsMapper.read();
        objectsFromData.getPersons().add(person);
        SafetyAlertsMapper.write(objectsFromData);

    }

    @Override
    public void update(Person person, int index) {
        ObjectFromData objectsFromData = SafetyAlertsMapper.read();
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

        ObjectFromData objectsFromData = SafetyAlertsMapper.read();
        objectsFromData.setPersons(person);
        SafetyAlertsMapper.write(objectsFromData);

    }
}

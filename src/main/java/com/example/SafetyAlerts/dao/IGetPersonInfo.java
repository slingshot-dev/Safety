package com.example.SafetyAlerts.dao;

import com.example.SafetyAlerts.modeles.Person;

import java.io.IOException;
import java.util.List;

public interface IGetPersonInfo {

    List<Person> getPersonFindByName(String firstname, String lastname);

    List<Person> getPersonAll();
}

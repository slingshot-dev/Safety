package com.example.SafetyAlerts.dao;

import com.example.SafetyAlerts.modeles.MedicalRecord;
import com.example.SafetyAlerts.modeles.Person;
import com.example.SafetyAlerts.modeles.PersonList;

import java.util.List;

public interface IGetPersonInfo {

    PersonList getPersonFindByName(String firstname, String lastname);

}

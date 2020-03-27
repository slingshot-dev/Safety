package com.example.SafetyAlerts.dao;

import com.example.SafetyAlerts.modeles.MedicalRecord;
import com.example.SafetyAlerts.modeles.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface IGetPersonInfo {

    ArrayList<String> getPersonFindByName(String firstname, String lastname);

    List<Person> getPersonAll();

    List<MedicalRecord> getMedAll();


}

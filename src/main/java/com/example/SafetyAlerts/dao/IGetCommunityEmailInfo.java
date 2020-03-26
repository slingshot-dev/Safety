package com.example.SafetyAlerts.dao;

import com.example.SafetyAlerts.modeles.Person;

import java.util.List;
import java.util.Map;

public interface IGetCommunityEmailInfo {

    List<String> getEmail(String city);

    List<Person> getPersonAll();

}

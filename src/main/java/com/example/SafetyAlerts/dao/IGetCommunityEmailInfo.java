package com.example.SafetyAlerts.dao;

import com.example.SafetyAlerts.modeles.Person;

import java.util.List;

public interface IGetCommunityEmailInfo {

    Object getEmail(String city);

    List<Person> getPersonAll();

}

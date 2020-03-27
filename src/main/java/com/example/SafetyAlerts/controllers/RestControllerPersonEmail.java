package com.example.SafetyAlerts.controllers;


import com.example.SafetyAlerts.dao.GetCommunityEmailInfo;
import com.example.SafetyAlerts.modeles.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/communityEmail")
public class RestControllerPersonEmail {

    @Autowired
    private GetCommunityEmailInfo getCommunityEmailInfo;

    @GetMapping
    public List<String> getEmail(String city) {

        return getCommunityEmailInfo.getEmail(city);
    }


}

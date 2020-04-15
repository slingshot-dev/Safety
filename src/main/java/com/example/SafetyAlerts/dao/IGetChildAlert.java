package com.example.SafetyAlerts.dao;

import com.example.SafetyAlerts.modeles.ChildAlertUrl;

import java.util.ArrayList;

public interface IGetChildAlert {

    ArrayList<ChildAlertUrl> getChildAlert(String address);


}

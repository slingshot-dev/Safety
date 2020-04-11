package com.example.SafetyAlerts.dao;

import com.example.SafetyAlerts.modeles.Firestation;
import com.example.SafetyAlerts.modeles.Person;

public interface ISetNewFirestation {

    void setAddFirestation(Firestation addFirestation);

    void setUpdateFirestation(Firestation putFirestation);

    void setRemoveFirestation(Firestation removeFirestation);

}

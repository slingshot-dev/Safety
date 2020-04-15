package com.example.SafetyAlerts.controllers;

import com.example.SafetyAlerts.dao.IGetChildAlert;
import com.example.SafetyAlerts.modeles.ChildAlertUrl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/childAlert")
public class RestControllerChildAlert {

    // ajouter le constructeur plutot que l'@Autowired. L'@Autowired est globalement non recommandé par SprinTeam
    private final IGetChildAlert igetChildAlert;

    public RestControllerChildAlert(IGetChildAlert igetChildAlert) {
        this.igetChildAlert = igetChildAlert;
    }



    @GetMapping
    // @ResponseBody
    public ArrayList<ChildAlertUrl> getChildAlert(String address)  {

        return igetChildAlert.getChildAlert(address);
    }

}

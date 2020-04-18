package com.example.SafetyAlerts.controllers;

import com.example.SafetyAlerts.modeles.MedicalRecord;
import com.example.SafetyAlerts.services.SetMedic;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicalRecord")
public class RestControllerMedicPPD {

    SetMedic setMedic = new SetMedic();

    @PostMapping("/post")
    public void addMedic(MedicalRecord addMedicalRecord) {
        setMedic.setAddMedic(addMedicalRecord);
    }

    @PutMapping("/put")
    public void updateMedic(MedicalRecord putMedicalRecord){
        setMedic.setUpdateMedic(putMedicalRecord);
    }

    @DeleteMapping("/delete")
    public void removeMedic(MedicalRecord removeMedicalRecord){
        setMedic.setRemoveMedic(removeMedicalRecord);
    }

}

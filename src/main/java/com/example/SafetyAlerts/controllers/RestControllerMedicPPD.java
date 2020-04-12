package com.example.SafetyAlerts.controllers;

import com.example.SafetyAlerts.dao.SetNewFirestation;
import com.example.SafetyAlerts.dao.SetNewMedic;
import com.example.SafetyAlerts.modeles.Firestation;
import com.example.SafetyAlerts.modeles.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicalRecord")
public class RestControllerMedicPPD {

        @Autowired
        SetNewMedic setNewMedic;

        @PostMapping("/post")
        public void addMedic(MedicalRecord addMedicalRecord) {
            setNewMedic.setAddMedic(addMedicalRecord);
        }

        @PutMapping("/put")
        public void updateMedic(MedicalRecord putMedicalRecord){
            setNewMedic.setUpdateMedic(putMedicalRecord);
        }

        @DeleteMapping("/delete")
        public void removeMedic(MedicalRecord removeMedicalRecord){
            setNewMedic.setRemoveMedic(removeMedicalRecord);
        }


/*    public @ResponseBody

    ResponseEntity<String> post() {


        return new ResponseEntity<String>("POST Response", HttpStatus.OK);
    }*/

    }






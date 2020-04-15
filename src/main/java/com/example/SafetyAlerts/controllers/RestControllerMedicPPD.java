package com.example.SafetyAlerts.controllers;

import com.example.SafetyAlerts.dao.ISetNewMedic;
import com.example.SafetyAlerts.modeles.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicalRecord")
public class RestControllerMedicPPD {

        private final ISetNewMedic isetNewMedic;
        public RestControllerMedicPPD(ISetNewMedic isetNewMedic) {
            this.isetNewMedic = isetNewMedic;
        }


        @PostMapping("/post")
        public void addMedic(MedicalRecord addMedicalRecord) {
            isetNewMedic.setAddMedic(addMedicalRecord);
        }

        @PutMapping("/put")
        public void updateMedic(MedicalRecord putMedicalRecord){
            isetNewMedic.setUpdateMedic(putMedicalRecord);
        }

        @DeleteMapping("/delete")
        public void removeMedic(MedicalRecord removeMedicalRecord){
            isetNewMedic.setRemoveMedic(removeMedicalRecord);
        }
    }






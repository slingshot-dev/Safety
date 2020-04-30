package com.example.SafetyAlerts.controllers;

import com.example.SafetyAlerts.modeles.MedicalRecord;
import com.example.SafetyAlerts.services.MedicService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicalRecord")
public class RestControllerMedicPPD {
    private static final Logger logger = LogManager.getLogger(RestControllerMedicPPD.class);
    private final MedicService medicService;

    public RestControllerMedicPPD(MedicService medicService) {
        this.medicService = medicService;
    }


    @PostMapping("/post")
    public void addMedic(MedicalRecord addMedicalRecord) throws Exception {

        if (addMedicalRecord.getFirstName().isEmpty() || addMedicalRecord.getLastName().isEmpty() || addMedicalRecord.getBirthdate().isEmpty() || addMedicalRecord.getMedications().isEmpty() || addMedicalRecord.getAllergies().isEmpty()) {
            logger.error("One or more Parameters Firstname, Lastname, Bithdate, Medications & Allergies are missing");
            throw new Exception("Parameters : Firstname, Lastname, Bithdate, Medications & Allergies, are necessary");
        } else {
            logger.info("Add Medicals Records OK");
            medicService.setAddMedic(addMedicalRecord);
        }
    }

    @PutMapping("/put")
    public void updateMedic(MedicalRecord putMedicalRecord) throws Exception {

        if (putMedicalRecord.getFirstName().isEmpty() || putMedicalRecord.getLastName().isEmpty() || putMedicalRecord.getBirthdate().isEmpty() || putMedicalRecord.getMedications().isEmpty() || putMedicalRecord.getAllergies().isEmpty()) {
            logger.error("One or more Parameters Firstname, Lastname, Bithdate, Medications & Allergies are missing");
            throw new Exception("Parameters : Firstname, Lastname, Bithdate, Medications & Allergies, are necessary");
        } else {
            medicService.setUpdateMedic(putMedicalRecord);
            logger.info("Update Medicals Records OK");
        }
    }

    @DeleteMapping("/delete")
    public void removeMedic(MedicalRecord removeMedicalRecord) throws Exception {

        if (removeMedicalRecord.getFirstName().isEmpty() || removeMedicalRecord.getLastName().isEmpty()) {
            logger.error("One or more Parameters Firstname, Lastname are missing");
            throw new Exception("Parameters : Firstname, Lastname");
        } else {
            medicService.setRemoveMedic(removeMedicalRecord);
            logger.info("Delete Medicals Records OK");
        }
    }
}

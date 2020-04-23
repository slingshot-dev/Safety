package com.example.SafetyAlerts.utils;

import com.example.SafetyAlerts.modeles.ObjectFromData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class SafetyAlertsMapper {

    /**
     *
     * @return : Renvoie l'Objet JSON complet comprenant les 3 objets Person, firestation, MedicalRecords
     */

    public ObjectFromData read() {

        ObjectFromData objectFromData = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            // Convert JSON string from file to Object
            objectFromData = objectMapper.readValue(new File("src/main/resources/data.json"), ObjectFromData.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return objectFromData;
    }


    /**
     *
     * @param objectFromData : Objet complet modifié par le programme pour ecriture vers le fichier JSON
     */

    public static void write(ObjectFromData objectFromData) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // write JSON Object to file
            objectMapper.writeValue(new File("src/main/resources/data.json"), objectFromData);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

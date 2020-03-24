package com.example.SafetyAlerts;

import com.example.SafetyAlerts.modeles.ObjectFromData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Component
public class SafetyAlertsMapper {

    public static ObjectFromData read() {

        ObjectFromData objectFromData = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            // Convert JSON string from file to Object
            objectFromData = objectMapper.readValue(new File("src/main/resources/data.json"), ObjectFromData.class);
            System.out.println(objectFromData);

        } catch (IOException e) {
            e.printStackTrace();
        }
    return objectFromData;
    }
}

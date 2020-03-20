package com.example.SafetyAlerts;

import com.example.SafetyAlerts.modeles.ObjectFromData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class SafetyAlertsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetyAlertsApplication.class, args);

		ObjectFromData objectFromData = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			// Convert JSON string from file to Object
			objectFromData = objectMapper.readValue(new File("src/main/resources/data.json"), ObjectFromData.class);
			System.out.println(objectFromData);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
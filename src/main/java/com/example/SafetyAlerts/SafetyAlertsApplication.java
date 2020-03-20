package com.example.SafetyAlerts;

import com.example.SafetyAlerts.modeles.Firestations;
import com.example.SafetyAlerts.modeles.Persons;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SafetyAlertsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetyAlertsApplication.class, args);

		Firestations firestations = new Firestations();
		firestations.firestations();

	}




}

package com.example.SafetyAlerts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 	 SafetyAlerts : Application d'envoie d'informations aux services d'urgence.
 * 	 @author : C. Guillet
 * 	 @version : 1.0 - Avril 2020
 */



@SpringBootApplication
public class SafetyAlertsApplication {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		SpringApplication.run(SafetyAlertsApplication.class, args);

	}
}
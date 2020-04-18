package com.example.SafetyAlerts;

import com.example.SafetyAlerts.modeles.ObjectFromData;
import com.example.SafetyAlerts.utils.SafetyAlertsMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class SafetyAlertsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetyAlertsApplication.class, args);

	}
}
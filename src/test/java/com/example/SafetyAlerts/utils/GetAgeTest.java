package com.example.SafetyAlerts.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetAgeTest {


    @Test
    public void calculateFareCar() {

        // Arrange
        String birthdate = "03/28/1973";

        // Act & Assert
        assertEquals("47", GetAge.getAge(birthdate));
    }
}

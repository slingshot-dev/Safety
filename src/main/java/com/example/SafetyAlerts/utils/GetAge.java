package com.example.SafetyAlerts.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class GetAge {

    public static String getAge(String birthDate) {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
                .withLocale(Locale.FRENCH);

        LocalDate date = LocalDate.parse(birthDate, formatter);
        int calculAge = Period.between(date, localDate).getYears();

        return String.valueOf(calculAge);
    }


}

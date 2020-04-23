package com.example.SafetyAlerts.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class GetAge {

    /** Convertie une date en Age
     *
     * @param birthDate : Date de naissance de la personne
     * @return : Age de la Personne.
     */

    public static String getAge(String birthDate) {
        int calculAge = 0;

        if (!birthDate.equals("TbD")) {
            LocalDate localDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
                    .withLocale(Locale.FRENCH);

            LocalDate date = LocalDate.parse(birthDate, formatter);
            calculAge = Period.between(date, localDate).getYears();
        } else {
            return "TbD";
        }

        return String.valueOf(calculAge);
    }
}
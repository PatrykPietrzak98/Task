package com.kontial.cloud.service.cloudservice.util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PersonValidatorUtil {
    public static boolean isValidDate(String dateString){
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(dateString, dtf);
            return true;
        } catch (DateTimeException ex){
            return false;
        }
    }

    public static boolean isValidId(String id){
        return id.matches("^[A-Za-z]\\d{4}$");
    }
}

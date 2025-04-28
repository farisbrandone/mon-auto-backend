package com.example.monauto.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Convertion {
    public static Date convertStringToDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.parse(date, formatter);

        // Convert LocalDate to Date (if necessary)
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Float convertStringToFloat(String number) {
        return Float.parseFloat(number);
    }
}

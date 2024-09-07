package br.com.codegroup.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public static String formatDate(LocalDate date) {
        return date != null ? date.format(FORMATTER) : "";
    }
}


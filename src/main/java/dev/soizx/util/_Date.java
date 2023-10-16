package dev.soizx.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class _Date {
    public static DateTimeFormatter hoursFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static DateTimeFormatter allFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");


    public static String now(DateTimeFormatter dateTimeFormatter) {
        return dateTimeFormatter.format(LocalDateTime.now());
    }
}

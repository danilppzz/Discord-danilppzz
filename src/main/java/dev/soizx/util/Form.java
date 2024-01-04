package dev.soizx.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Form {

    public static DateTimeFormatter hoursFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static DateTimeFormatter allFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");


    public static String dateNow(DateTimeFormatter dateTimeFormatter) {
        return LocalDateTime.now().format(dateTimeFormatter);
    }

    public static String getLinks(String input) {
        String regex = "https?://[\\w/.-]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        StringBuilder jsonLinks = new StringBuilder("[");

        boolean firstLink = true;

        while (matcher.find()) {
            String link = matcher.group();
            if (!firstLink) {
                jsonLinks.append(", ");
            }
            jsonLinks.append("\"").append(link).append("\"");
            firstLink = false;
        }

        jsonLinks.append("]");

        if (firstLink) {
            return "[]";
        } else {
            return jsonLinks.toString();
        }
    }

    public static boolean isLinkInBlacklist(String input) {

        // Need to create a File .json of blacklisted links
        String blacklist = "https://example.com/";
        String regex = "https?://[\\w/.-]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String link = matcher.group();
            if (blacklist.equals(link)) {
                return true;
            }
        }
        return false;
    }
}

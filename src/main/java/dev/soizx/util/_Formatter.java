package dev.soizx.util;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class _Formatter {
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

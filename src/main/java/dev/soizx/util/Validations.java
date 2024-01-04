package dev.soizx.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validations {
    private static final Map<String, Instant> lastRequestTimes = new HashMap<>();
    private static final Duration requestCooldown = Duration.ofDays(7);

    public static void addToCooldownList(String userId) {
        Instant lastRequestTime = lastRequestTimes.get(userId);
        Instant currentTime = Instant.now();

        if (lastRequestTime == null || Duration.between(lastRequestTime, currentTime).compareTo(requestCooldown) >= 0) {
            lastRequestTimes.put(userId, currentTime);
        }
    }

    public static DateTimeFormatter hoursFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static DateTimeFormatter allFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");


    public static String dateNow(DateTimeFormatter dateTimeFormatter) {
        return LocalDateTime.now().format(dateTimeFormatter);
    }

    public static boolean isInCooldownList(String userId) {
        Instant lastRequestTime = lastRequestTimes.get(userId);
        Instant currentTime = Instant.now();

        return lastRequestTime == null || Duration.between(lastRequestTime, currentTime).compareTo(requestCooldown) >= 0;
    }

    public static boolean validateGitHubURL(String url) {
        String githubUrlPattern = "https://github.com/[\\w-]+";

        Pattern pattern = Pattern.compile(githubUrlPattern);

        Matcher matcher = pattern.matcher(url);

        return matcher.matches();
    }

    public static String getGitHubUsername(String url) {
        if (!validateGitHubURL(url)) return url;
        String githubUrlPattern = "https://github.com/([\\w-]+)";

        Pattern pattern = Pattern.compile(githubUrlPattern);

        Matcher matcher = pattern.matcher(url);

        if (matcher.matches()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }

    public static boolean validateURL(String url) {
        if (isLinkInBlacklist(url)) return false;
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            return false;
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
}

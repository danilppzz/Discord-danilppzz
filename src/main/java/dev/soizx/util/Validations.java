package dev.soizx.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
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
        if (Form.isLinkInBlacklist(url)) return false;
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }
}

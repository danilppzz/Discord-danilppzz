package dev.soizx.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadConfig {
    public static String Env(String value) {
        Properties properties = new Properties();

        try (InputStream input = LoadConfig.class.getClassLoader().getResourceAsStream(".env")) {
            if (input != null) {
                properties.load(input);
            } else {
                throw new FileNotFoundException("File Not Found");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return properties.getProperty(value.toUpperCase());
    }

    public static String Config(String value) {
        Properties properties = new Properties();

        try (InputStream input = LoadConfig.class.getClassLoader().getResourceAsStream("config.json")) {
            if (input != null) {
                properties.load(input);
            } else {
                throw new FileNotFoundException("File Not Found");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return properties.getProperty(value);
    }
}

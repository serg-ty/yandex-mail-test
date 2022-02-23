package ru.yandex.mail.development.conf;

import java.io.IOException;

public class Config {

    public static String getProperty(String property) {
        try {
            System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return System.getProperty(property);
    }
}

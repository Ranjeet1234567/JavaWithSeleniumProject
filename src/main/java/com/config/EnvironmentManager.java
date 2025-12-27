package com.config;

import com.utils.ConfigReader;

import java.util.Properties;


public class EnvironmentManager {

    private static String env;

    // Prevent object creation
    private EnvironmentManager() {}

    public static String getEnv() {

        if (env == null) {
            // 1️⃣ First priority: JVM argument
            env = System.getProperty("env");

            // 2️⃣ Second priority: config.properties
            if (env == null) {
                env = ConfigReader.getProperty("env");
            }

            // 3️⃣ Default fallback
            if (env == null) {
                env = "qa";
            }
        }

        return env;
    }
}


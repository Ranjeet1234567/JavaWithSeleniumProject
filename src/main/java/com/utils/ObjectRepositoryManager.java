package com.utils;
import com.repository.ObjectRepositoryReader;
import org.openqa.selenium.By;
import java.util.concurrent.ConcurrentHashMap;
import com.config.EnvironmentManager;

public class ObjectRepositoryManager {

    private static final ConcurrentHashMap<String, ObjectRepositoryReader> cache
            = new ConcurrentHashMap<>();

    public static By getLocator(String pageName, String elementName) {

        String key = EnvironmentManager.getEnv() + "_" + pageName;

        ObjectRepositoryReader reader =
                cache.computeIfAbsent(key, k -> new ObjectRepositoryReader(pageName));

        return reader.getLocator(elementName);
    }
}


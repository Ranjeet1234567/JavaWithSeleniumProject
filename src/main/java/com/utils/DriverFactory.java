package com.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // 🔥 THIS METHOD MUST SET DRIVER
    public static void initDriver() {
        WebDriver webDriver;

        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();

        driver.set(webDriver);
        // ✅ MOST IMPORTANT LINE
        System.out.println("Driver in BaseTest = " + DriverFactory.getDriver());

    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}

package com.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver(String browser) {

        WebDriver webDriver;

        switch (browser.toLowerCase()) {

            case "chrome":
                WebDriverManager.chromedriver()
                        .browserVersion("129")
                        .setup();

                ChromeOptions options = new ChromeOptions();
                options.setBinary("C:\\Users\\ranjeetverma\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe");
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
                webDriver = new ChromeDriver(options);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setAcceptInsecureCerts(true);
                // firefoxOptions.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe"); // if needed
                webDriver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                webDriver = new EdgeDriver(edgeOptions);
                break;

            default:
                throw new RuntimeException("Invalid browser: " + browser);
        }

        driver.set(webDriver); // ✅ MOST IMPORTANT LINE
        System.out.println("Driver initialized: " + browser);
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

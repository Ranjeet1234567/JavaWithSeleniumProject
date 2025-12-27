package com.utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.utils.DriverFactory; // Your WebDriver manager

public class ScreenshotUtil {

    // Return screenshot as bytes for Allure
    public static byte[] takeScreenshotBytes() {
        WebDriver driver = DriverFactory.getDriver();
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    // Save screenshot to file for ExtentReports
    public static String takeScreenshotFile(String name) {
        WebDriver driver = DriverFactory.getDriver();
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "reports/screenshots/" + name + ".png";
        try {
            Files.createDirectories(Paths.get("reports/screenshots"));
            Files.copy(srcFile.toPath(), Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }


}

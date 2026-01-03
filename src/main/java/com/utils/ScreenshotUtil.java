package com.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

public class ScreenshotUtil {

    private static final String SCREENSHOT_DIR = System.getProperty("user.dir") + "/reports/screenshots";

    static {
        File folder = new File(SCREENSHOT_DIR);
        if (!folder.exists()) folder.mkdirs();
    }

    /**
     * Capture screenshot with timestamp and return the file name.
     * Suitable for ExtentReports.
     */
    public static String captureScreenshot(String testName) {
        try {
            WebDriver driver = DriverFactory.getDriver(); // Your WebDriver instance
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = testName + "_" + timestamp + ".png";
            String destPath = SCREENSHOT_DIR + "/" + fileName;

            FileUtils.copyFile(srcFile, new File(destPath));

            return fileName; // only file name for ExtentReports

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Capture screenshot as byte array for Allure attachments.
     */
    public static byte[] getScreenshotAsBytes() {
        WebDriver driver = DriverFactory.getDriver();
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}

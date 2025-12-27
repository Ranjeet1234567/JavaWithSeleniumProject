package com.listeners;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import com.utils.ScreenshotUtil; // Your screenshot utility class

import java.io.ByteArrayInputStream;

public class TestListener implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    static {
        // Initialize ExtentReports
        ExtentSparkReporter spark = new ExtentSparkReporter("reports/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());

        // Take screenshot
        byte[] screenshotBytes = ScreenshotUtil.takeScreenshotBytes();
        if (screenshotBytes != null) {
            // Attach screenshot to ExtentReports
            test.get().addScreenCaptureFromPath(ScreenshotUtil.takeScreenshotFile(result.getMethod().getMethodName()));

            // Attach screenshot to Allure
            attachScreenshotToAllure(result.getMethod().getMethodName(), screenshotBytes);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    // Allure screenshot attachment
    // Attach screenshot to Allure report
    public static void attachScreenshotToAllure(String stepName, byte[] screenshotBytes) {
        if (screenshotBytes != null) {
            Allure.addAttachment(stepName, new ByteArrayInputStream(screenshotBytes));
        }
    }

}

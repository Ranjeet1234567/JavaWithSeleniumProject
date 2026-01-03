package com.listeners;

import com.aventstack.extentreports.*;
import com.reports.ExtentManager;
import com.utils.ScreenshotUtil;
import io.qameta.allure.Attachment;
import org.testng.*;

public class TestListener implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        // ✅ Initialize ONCE
        System.out.println(">>> TestListener onStart triggered <<<");
        extent = ExtentManager.getInstance();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest =
                extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        getTest().fail(result.getThrowable());
        if (!isFinalFailure(result)) {
            getTest().info("Retry in progress. Screenshot skipped.");
            return;
        }
        try {
            // Capture screenshot
            String screenshotFileName = ScreenshotUtil.captureScreenshot(result.getMethod().getMethodName());
            // Build relative path for Extent report
            String absolutePath = System.getProperty("user.dir") + "/reports/screenshots/" + screenshotFileName;
            getTest().addScreenCaptureFromPath(absolutePath, "Failed Test Screenshot");
        } catch (Exception e) {
            e.printStackTrace();
        }
}

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println(">>> TestListener onStart triggered <<<");
        // ✅ Write report ONCE
        extent.flush();
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    @Attachment(value = "Failure Screenshot", type = "image/png")
    public byte[] attachAllureScreenshot() {
        return ScreenshotUtil.getScreenshotAsBytes();
    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not needed in most cases
    }
    // ✅ FINAL FAILURE CHECK
    private boolean isFinalFailure(ITestResult result) {
        Object retryAnalyzer = result.getMethod().getRetryAnalyzer(result);
        if (retryAnalyzer == null) return true;

        if (retryAnalyzer instanceof RetryAnalyzer) {
            RetryAnalyzer retry = (RetryAnalyzer) retryAnalyzer;
            return retry.getRetryCount() >= retry.getMaxRetryCount();
        }
        return true;
    }
}

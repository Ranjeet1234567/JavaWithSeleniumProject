package com.reports;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentManager {

    private static ExtentReports extent;
    private static String reportDir;

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {

            // ✅ FIXED REPORT DIRECTORY
            reportDir = System.getProperty("user.dir") + "/reports/extent";

            // ✅ DELETE OLD REPORTS
            deleteDirectory(new File(reportDir));

            // ✅ CREATE REPORT DIRECTORY
            new File(reportDir).mkdirs();

            // ✅ EXTENT REPORT PATH
            ExtentSparkReporter spark = new ExtentSparkReporter(reportDir + "/extent-report.html");
            spark.config().setReportName("Automation Test Report");
            spark.config().setDocumentTitle("Hybrid Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Framework", "Selenium + TestNG");
        }
        return extent;
    }

    public static String getReportDir() {
        return reportDir;
    }

    // Utility method to delete old reports
    private static void deleteDirectory(File directory) {
        if (!directory.exists()) return;

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        directory.delete(); // delete root folder as well
    }
}

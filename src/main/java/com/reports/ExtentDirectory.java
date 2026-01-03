package com.reports;

import java.io.File;

public class ExtentDirectory {

    private static String reportDir = System.getProperty("user.dir") + "/reports/extent";

    public static String getReportDir() {
        File dir = new File(reportDir);
        if (!dir.exists()) {
            dir.mkdirs(); // create directory if it doesn't exist
        }
        return reportDir;
    }
}

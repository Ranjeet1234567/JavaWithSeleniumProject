package com.base;

import com.utils.ConfigReader;
import com.utils.DriverFactory;
import com.utils.EmailUtils;
import com.utils.JsonDataReader;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTests {
    protected String env;
    protected String dataFile;

    @BeforeMethod
    @Parameters({"browser", "env", "dataFile" })
    public void setup(@Optional("chrome") String browser,@Optional("qa") String env,
                      @Optional("login.json") String dataFile) {

        String url = ConfigReader.getProperty("url");

        this.env = System.getProperty("env", env);
        this.dataFile = dataFile;

        JsonDataReader.load(this.env, this.dataFile);

        DriverFactory.initDriver(browser);   // driver is set here

        if (url == null) {
            throw new RuntimeException("URL not found for env: " + env);
        }

        DriverFactory.getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
        DriverFactory.getDriver().manage().window().maximize();
        DriverFactory.getDriver().get(url);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
        // AFTER screenshot
    }
    @AfterSuite
    public void sendReportByEmail() {
        EmailUtils.sendEmailWithReport(
                System.getProperty("user.dir") + "/reports/extent/extent-report.html"
        );
    }

}
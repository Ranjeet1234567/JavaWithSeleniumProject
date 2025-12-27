package com.base;

import com.utils.ConfigReader;
import com.utils.DriverFactory;
import com.utils.JsonDataReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTests {
    protected String env;
    protected String dataFile;

    @BeforeMethod
    @Parameters({ "env", "dataFile" })
    public void setup(@Optional("qa") String env,
                      @Optional("login.json") String dataFile) {

        String url = ConfigReader.getProperty("url");

        this.env = System.getProperty("env", env);
        this.dataFile = dataFile;

        JsonDataReader.load(this.env, this.dataFile);

        DriverFactory.initDriver();   // driver is set here

        if (url == null) {
            throw new RuntimeException("URL not found for env: " + env);
        }

        DriverFactory.getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        DriverFactory.getDriver().get(url);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();   // AFTER screenshot
    }
}
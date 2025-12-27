package com.utils;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;

public class AllureUtil {

    @Attachment(value = "{name}", type = "image/png")
    public static byte[] attachScreenshot(String name) {
        return ((TakesScreenshot) DriverFactory.getDriver())
                .getScreenshotAs(OutputType.BYTES);
    }
}


package com.utils;

import io.qameta.allure.Attachment;

import java.nio.file.Files;
import java.nio.file.Paths;

public class AllureUtil {

    @Attachment(value = "Execution Logs", type = "text/plain")
    public static byte[] attachLogFile() {
        try {
            return Files.readAllBytes(
                    Paths.get("logs/automation.log"));
        } catch (Exception e) {
            return "Unable to attach logs".getBytes();
        }
    }
}

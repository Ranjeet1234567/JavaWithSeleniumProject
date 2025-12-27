package com.utils;
import com.aventstack.extentreports.Status;
import com.listeners.TestListener;
import org.testng.Assert;

public class StepAssert {

    public static void assertTrue(boolean condition, String stepName) {

        StepContext.set(stepName);

        try {
            Assert.assertTrue(condition, stepName);
            TestListener.getTest().log(Status.PASS, stepName);

        } catch (AssertionError e) {
            throw e;   // 🔥 Listener will handle screenshot
        }
    }
}

package com.utils;

import java.util.function.Supplier;

public class Waiter {

    private static final int DEFAULT_TIMEOUT = 20; // seconds

    /**
     * Waits until the condition in lambda returns true
     * @param condition lambda that returns boolean
     */
    public static void waitFor(Supplier<Boolean> condition) {
        waitFor(condition, DEFAULT_TIMEOUT);
    }

    public static void waitFor(Supplier<Boolean> condition, int timeoutSeconds) {
        long endTime = System.currentTimeMillis() + timeoutSeconds * 1000;

        while (System.currentTimeMillis() < endTime) {
            try {
                if (condition.get()) {
                    return; // condition passed
                }
            } catch (Exception ignored) {
            }

            try {
                Thread.sleep(500); // polling interval
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrupted while waiting", e);
            }
        }

        throw new RuntimeException("Wait condition failed");
    }
}



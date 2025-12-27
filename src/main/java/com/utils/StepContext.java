package com.utils;

public class StepContext {
    public static ThreadLocal<String> stepName = new ThreadLocal<>();

    public static void set(String step) {
        stepName.set(step);
    }

    public static String get() {
        return stepName.get();
    }

    public static void clear() {
        stepName.remove();
    }
}


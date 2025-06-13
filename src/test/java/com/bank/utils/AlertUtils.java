package com.bank.utils;

public class AlertUtils {
    
    /**
     * Handles the alert wait functionality after accepting an alert
     * @param elementUtils The WebElementUtils instance to use for waiting
     */
    public static void handleAlertWait(WebElementUtils elementUtils) {
        try {
            Thread.sleep(1000);
            elementUtils.waitForPageLoaded();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
} 
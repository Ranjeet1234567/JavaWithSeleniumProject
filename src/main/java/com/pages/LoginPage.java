package com.pages;

import com.driver.SeleniumClient;
import com.utils.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginPage {

    private static final String PAGE_NAME = "Login";
    private final SeleniumClient seleniumClient;
    protected static Logger log =
            LogManager.getLogger(LoginPage.class);

    public LoginPage() {
        seleniumClient = new SeleniumClient(DriverFactory.getDriver());
    }

    private void setPassword(String password) {
        log.debug("Entering password");
        Waiter.waitFor(() ->
                seleniumClient.element(PAGE_NAME, "PasswordField").isDisplayed()
        );
        seleniumClient.element(PAGE_NAME, "PasswordField").setText(password);
    }

    private void setUsername(String username) {
        log.debug("Entering username");
        seleniumClient.element(PAGE_NAME, "UserNameField").isDisplayed();
        seleniumClient.element(PAGE_NAME, "UserNameField").setText(username);
    }

    private void clickLogin() {
        log.debug("Click on Login button");
        seleniumClient.element(PAGE_NAME, "LoginButton").click();
    }

    public void login(String user, String pass) {
        setUsername(user);
        setPassword(pass);
        clickLogin();
    }
    public boolean verifySwagLabsScreen() {
        log.debug("Verify home screen");
        return seleniumClient.element(PAGE_NAME, "SwagLabsScreen").isDisplayed();
    }
    public boolean verifyLoginScreen() {
        log.debug("Verify login screen");
        return seleniumClient.element(PAGE_NAME, "LoginScreen").isDisplayed();
    }

}

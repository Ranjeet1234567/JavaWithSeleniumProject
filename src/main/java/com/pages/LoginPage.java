package com.pages;

import com.driver.SeleniumClient;
import com.utils.*;

public class LoginPage {

    private static final String PAGE_NAME = "Login";
    private final SeleniumClient seleniumClient;

    public LoginPage() {
        seleniumClient = new SeleniumClient(DriverFactory.getDriver());
    }

    private void setPassword(String password) {
        Waiter.waitFor(() ->
                seleniumClient.element(PAGE_NAME, "PasswordField").isDisplayed()
        );
        seleniumClient.element(PAGE_NAME, "PasswordField").setText(password);
    }

    private void setUsername(String username) {
        seleniumClient.element(PAGE_NAME, "UserNameField").isDisplayed();
        seleniumClient.element(PAGE_NAME, "UserNameField").setText(username);
    }

    private void clickLogin() {
        seleniumClient.element(PAGE_NAME, "LoginButton").click();
    }

    public void login(String user, String pass) {
        setUsername(user);
        setPassword(pass);
        clickLogin();
    }
    public boolean verifySwagLabsScreen() {
        return seleniumClient.element(PAGE_NAME, "SwagLabsScreen").isDisplayed();
    }
    public boolean verifyLoginScreen() {
        return seleniumClient.element(PAGE_NAME, "LoginScreen").isDisplayed();
    }

}

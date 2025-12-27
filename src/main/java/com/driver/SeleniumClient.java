package com.driver;
import com.utils.ObjectRepositoryManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumClient {

    private final WebDriver driver;

    public SeleniumClient(WebDriver driver) {
        this.driver = driver;
    }

    // 🔥 THIS enables: seleniumClient.element("Login","PasswordField").setText()
    public UIElement element(String pageName, String elementName) {

        By by = ObjectRepositoryManager.getLocator(pageName, elementName);
        WebElement webElement = driver.findElement(by);

        return new UIElement(webElement);
    }
}


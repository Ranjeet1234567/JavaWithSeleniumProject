package com.driver;
import org.openqa.selenium.WebElement;

public class UIElement {

    private WebElement element;

    public UIElement(WebElement element) {
        this.element = element;
    }

    public void setText(String text) {
        element.clear();
        element.sendKeys(text);
    }

    public void click() {
        element.click();
    }

    public boolean isDisplayed() {
        return element.isDisplayed();
    }

    public String getText() {
        return element.getText();
    }
}


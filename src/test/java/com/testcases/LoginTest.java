package com.testcases;

import com.base.BaseTests;
import com.pages.LoginPage;
import com.utils.JsonDataReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTests {
    LoginPage login;
    @Test
    public void testLogin() {
        login.login(JsonDataReader.get("username"),JsonDataReader.get("password"));
        Assert.assertTrue(login.verifySwagLabsScreen());
    }
    /**
     * The annotated method will be run before all the test methods in the current class have been run.
     */
    @BeforeMethod()
    public void assignPages() {
        login = new LoginPage();
    }

}

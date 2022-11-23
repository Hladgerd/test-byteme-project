package com.codecool.byteme;

import com.codecool.byteme.pages.FeedPage;
import com.codecool.byteme.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogoutSteps {
    private LoginPage loginPage;
    private FeedPage feedPage;

    @Before
    public void init(){
        loginPage = new LoginPage();
        feedPage = new FeedPage();
    }

    @Given("The user is logged in to Byte.me")
    public void openLogin() {
        loginPage.openLoginPage();
        loginPage.login();
    }

    @When("clicks Logout button")
    public void logout() {
        feedPage.logOutCurrentUser();
    }

    @Then("Login page is visible")
    public void loginPageIsVisible() {
        loginPage.reOpenLoginPage();
        assertTrue(loginPage.isLoginButtonVisible());
    }

    @After
    public void close() {
        loginPage.closeWebDriver();
    }
}

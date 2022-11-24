package com.codecool.byteme;

import com.codecool.byteme.pages.FeedPage;
import com.codecool.byteme.pages.LoginPage;
import com.codecool.byteme.pages.ProfilePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class LoginSteps {
    private LoginPage loginPage;
    private FeedPage feedPage;
    private ProfilePage profilePage;

    @Before
    public void init(){
        loginPage = new LoginPage();
        feedPage = new FeedPage();
        profilePage = new ProfilePage();
    }

    @Given("The user is on Byte.me login page")
    public void openLogin() {
        loginPage.openLoginPage();
    }

    @When("enters {string}, {string} and clicks the Login button")
    public void login(String email, String password) {
        loginPage.login(email, password);
    }

    @Then("logout button is visible")
    public void logoutButtonIsVisible() {
        assertTrue(feedPage.isLogoutButtonVisible());
    }

    @And("registered name {string} equals with name on Profile page")
    public void checkEmail(String fullname) {
        feedPage.openProfilePage();
        assertEquals(fullname, profilePage.getFullName());
    }

    @When("doesn't fill up credentials, just clicks the Login button")
    public void clickLoginButton() {
        loginPage.login("", "");
    }

    @Then("feed page is not visible")
    public void feedPageIsNotVisible() {
        assertFalse(feedPage.isLogoutButtonVisible());
    }

    @Then("alert message received")
    public void getAlert() {
        assertEquals("No email given", loginPage.getAlertMessage());
    }

    @After
    public void close() {
        loginPage.closeWebDriver();
    }
}

package com.codecool.byteme;

import com.codecool.byteme.pages.FeedPage;
import com.codecool.byteme.pages.LoginPage;
import com.codecool.byteme.pages.ProfilePage;
import com.codecool.byteme.pages.RegistrationPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationSteps {
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private FeedPage feedPage;
    private ProfilePage profilePage;
    String email;

    @Before
    public void init(){
        loginPage = new LoginPage();
        registrationPage = new RegistrationPage();
        feedPage = new FeedPage();
        profilePage = new ProfilePage();
    }

    @Given("the user is on Registration page")
    public void openRegistration() {
        loginPage.openRegistrationForm();
    }

    @When("fills up {string}, email, {string}, {string} and submits registration")
    public void register(String fullName, String age, String password) {
        email = Util.generateRandomString() + "@byte.me";
        registrationPage.registerUser(fullName, email, age, password);
    }

    @Then("logs in successfully with registered email and {string}")
    public void login(String password) {
        loginPage.login(email, password);
        assertTrue(feedPage.isLogoutButtonVisible());
    }

    @And("the given {string} equals with the name on Profile page")
    public void theGivenEqualsWithTheNameOnProfilePage(String fullName) {
        feedPage.openProfilePage();
        assertEquals(fullName, profilePage.getFullName());
    }

    @After
    public void close() {
        loginPage.closeWebDriver();
    }
}

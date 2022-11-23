package com.codecool.byteme;

import com.codecool.byteme.pages.FeedPage;
import com.codecool.byteme.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostSteps {
    private LoginPage loginPage;
    private FeedPage feedPage;
    private Boolean shouldBeDeleted;

    @Before
    public void init() {
        loginPage = new LoginPage();
        feedPage = new FeedPage();
        loginPage.openLoginPage();
        loginPage.login();
        shouldBeDeleted = false;
    }

    @Given("the user is on Feed page")
    public void openFeedPage() {
        loginPage.openFeedPage();
    }

    @When("fills up post {string} and {string} and clicks Add button")
    public void fillsUpForm(String title, String body) {
        feedPage.createNewPost(title, body);
        shouldBeDeleted = true;
    }

    @Then("the given {string} equals the title of the latest post")
    public void checkTitle(String title) {
        assertEquals(title, feedPage.getLatestPostTitle());
    }

    @And("the given {string} equals the body of the latest post")
    public void checkBody(String body) {
        assertEquals(body, feedPage.getLatestPostBody());
    }

    @After
    public void close() {
        if(shouldBeDeleted) feedPage.deleteNewPost();
        loginPage.closeWebDriver();
    }
}

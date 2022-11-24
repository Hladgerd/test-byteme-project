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
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PostSteps {
    private LoginPage loginPage;
    private FeedPage feedPage;
    private Boolean shouldBeDeleted;
    private String uniqueTitle;
    private String uniqueBody;

    @Before
    public void init() {
        loginPage = new LoginPage();
        feedPage = new FeedPage();
        shouldBeDeleted = false;
    }

    @Given("the user is on Feed page")
    public void openFeedPage() {
        loginPage.login();
        feedPage.openFeeds();
    }

    @When("fills up post title and body and clicks Add button")
    public void fillsUpForm() {
        uniqueTitle = Util.generateRandomString();
        uniqueBody = Util.generateRandomString();
        feedPage.createNewPost(uniqueTitle, uniqueBody);
        shouldBeDeleted = true;
    }

    @Then("the given title equals the title of the latest post")
    public void checkTitleEquals() {
        assertEquals(uniqueTitle, feedPage.getNewlyCreatedPostTitle(uniqueTitle));
    }

    @And("the given body equals the body of the latest post")
    public void checkBodyEquals() {
        assertEquals(uniqueBody, feedPage.getNewlyCreatedPostBody(uniqueBody));
    }

    @Given("a new post has been created")
    public void post() {
        uniqueTitle = Util.generateRandomString();
        uniqueBody = Util.generateRandomString();
        feedPage.createNewPost(uniqueTitle, uniqueBody);
    }

    @When("user deletes the new post")
    public void deletePost() {
        feedPage.deleteNewPost(uniqueTitle);
    }

    @Then("the created post title is different from last post's title")
    public void checkTitleDiffers() {
        assertNotEquals(uniqueTitle, feedPage.getLatestPostTitle());
    }

    @And("the created post body is different from last post's body")
    public void checkBodyDiffers() {
        assertNotEquals(uniqueBody, feedPage.getLatestPostBody());
    }

    @After
    public void close() {
        if(shouldBeDeleted) feedPage.deleteNewPost(uniqueTitle);
        loginPage.closeWebDriver();
    }
}

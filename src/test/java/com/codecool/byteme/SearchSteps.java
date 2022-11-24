package com.codecool.byteme;

import com.codecool.byteme.pages.FeedPage;
import com.codecool.byteme.pages.LoginPage;
import com.codecool.byteme.pages.ProfilePage;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchSteps {
    private ProfilePage profilePage;
    private FeedPage feedPage;

    @Before
    public void init() {
        profilePage = new ProfilePage();
        feedPage = new FeedPage();
    }

    @When("searches for {string}")
    public void searchName(String name) {
        feedPage.searchFor(name);
    }

    @And("selects the first result")
    public void select() {
        feedPage.selectTheFirstResult();
    }

    @Then("given {string} equals with the name on searched user's profile page")
    public void givenEqualsWithTheNameOnSearchedUserSProfilePage(String name) {
        assertEquals(name, profilePage.getFullName());
    }
}

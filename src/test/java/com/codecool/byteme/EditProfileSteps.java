package com.codecool.byteme;

import com.codecool.byteme.pages.FeedPage;
import com.codecool.byteme.pages.LoginPage;
import com.codecool.byteme.pages.ProfilePage;
import com.codecool.byteme.pages.RegistrationPage;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditProfileSteps {
    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private FeedPage feedPage;
    private ProfilePage profilePage;

    @Before
    public void init(){
        registrationPage = new RegistrationPage();
        loginPage = new LoginPage();
        feedPage = new FeedPage();
        profilePage = new ProfilePage();
    }

    @Given("edit form is opened")
    public void openForm() {
        String fullName = "Aladar";
        String age = "6";
        String password = "123";
        String email = Util.generateRandomString() + "@byte.me";
        loginPage.openRegistrationForm();
        registrationPage.registerUser(fullName, email, age, password);
        loginPage.openLoginPage();
        loginPage.login(email, password);
        feedPage.openProfilePage();
        profilePage.openEditPanel();
    }

    @When("user deletes current name, enters {string}, saves field and saves changes")
    public void changeName(String newName) {
        profilePage.editName(newName);
        profilePage.saveChanges();
    }

    @Then("name on profile page equals with the given {string}")
    public void checkNewName(String newName) {
        assertEquals(newName, profilePage.getFullName());
    }

    @When("user deletes current age, enters {string}, saves field and saves changes")
    public void changeAge(String newAge) {
        profilePage.editAge(newAge);
        profilePage.saveChanges();
    }

    @Then("age on profile page equals with the given {string}")
    public void checkNewAge(String newAge) {
        assertEquals(newAge, profilePage.getAge());
    }
}

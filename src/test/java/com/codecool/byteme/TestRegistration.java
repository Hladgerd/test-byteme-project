package com.codecool.byteme;

import com.codecool.byteme.pages.FeedPage;
import com.codecool.byteme.pages.LoginPage;
import com.codecool.byteme.pages.ProfilePage;
import com.codecool.byteme.pages.RegistrationPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.EmptySource;

import static org.junit.jupiter.api.Assertions.*;

public class TestRegistration {

    private LoginPage loginPage;
    private FeedPage feedPage;
    private ProfilePage profilePage;

    private RegistrationPage registrationPage;

    private boolean shouldLogOut;

    @BeforeEach
    void init() {
        loginPage = new LoginPage();
        feedPage = new FeedPage();
        profilePage = new ProfilePage();
        registrationPage = new RegistrationPage();
    }

    @AfterEach
    void close() {
        loginPage.closeWebDriver();
    }

    @ParameterizedTest
    @DisplayName("Register user with correct credentials")
    @CsvFileSource(resources = "/newUserCredentials.csv", numLinesToSkip = 1, delimiter = ';')
    public void registerSuccessfully(String description,String fullName,  String email, String age, String password){
        loginPage.reOpenLoginPage();
        loginPage.openRegistrationForm();
        registrationPage.fillOutForm(fullName, email, age, password);
        registrationPage.submitRegistration();
        loginPage.login(email, password);
        feedPage.openProfilePage();
        assertEquals(fullName, profilePage.getFullName());
    }


    @ParameterizedTest
    @DisplayName("Register with empty credentials")
    @EmptySource
    public void loginSuccessfully(String email) {
        shouldLogOut = true;
        loginPage.login(email, "" ); // TODO: add password
        assertTrue(feedPage.isLogoutButtonVisible());

        feedPage.openProfilePage();

    }

    //TODO: implement more test for already existing user


}

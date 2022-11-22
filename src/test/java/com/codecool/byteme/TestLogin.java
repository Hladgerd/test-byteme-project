package com.codecool.byteme;

import com.codecool.byteme.pages.FeedPage;
import com.codecool.byteme.pages.LoginPage;
import com.codecool.byteme.pages.ProfilePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class TestLogin {
    private LoginPage loginPage;
    private FeedPage feedPage;
    private ProfilePage profilePage;

    private boolean shouldLogOut;

    @BeforeEach
    void init() {
        loginPage = new LoginPage();
        feedPage = new FeedPage();
        profilePage = new ProfilePage();
        shouldLogOut = false;
    }

    @AfterEach
    void close() {
        if (shouldLogOut) feedPage.logOutCurrentUser();
        loginPage.closeWebDriver();
    }

    @ParameterizedTest
    @DisplayName("Login registered user with correct credentials")
    @CsvFileSource(resources = "/userCredentials.csv", numLinesToSkip = 1, delimiter = ';')
    public void loginSuccessfully(String description, String email, String fullName) {
        shouldLogOut = true;
        loginPage.login(email);
        assertTrue(feedPage.isLogoutButtonVisible());

        feedPage.openProfilePage();
        assertEquals(fullName, profilePage.getFullName());
    }

    @ParameterizedTest
    @DisplayName("Login registered user with wrong credentials")
    @CsvFileSource(resources = "/userCredentialsWrong.csv", numLinesToSkip = 1, delimiter = ';')
    public void loginWithWrongCredentials(String description, String email) {
        loginPage.login(email);
        assertFalse(feedPage.isLogoutButtonVisible());
    }

    @ParameterizedTest
    @DisplayName("Login with empty credentials")
    @EmptySource
    public void loginWithEmptyCredentials(String email) {
        loginPage.login(email);
        assertEquals("No email given", loginPage.getAlertMessage());
    }

    @ParameterizedTest
    @DisplayName("Login non registered users")
    @ValueSource(strings = "noname@byte.me")
    public void nonRegisteredUserlogin(String email) {
        loginPage.login(email);
        assertFalse(feedPage.isLogoutButtonVisible());
    }

}

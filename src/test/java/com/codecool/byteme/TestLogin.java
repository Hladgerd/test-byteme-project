package com.codecool.byteme;

import com.codecool.byteme.pages.FeedPage;
import com.codecool.byteme.pages.LoginPage;
import com.codecool.byteme.pages.ProfilePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

public class TestLogin {
    LoginPage loginPage;
    FeedPage feedPage;
    ProfilePage profilePage;

    @BeforeEach
    void init() {
        loginPage = new LoginPage();
        feedPage = new FeedPage();
        profilePage = new ProfilePage();
    }

    @AfterEach
    void close() {
        loginPage.closeWebDriver();
    }

    @ParameterizedTest
    @DisplayName("Login registered user with correct credentials")
    @CsvFileSource(resources = "/userCredentials.csv", numLinesToSkip = 1, delimiter = ';')
    public void loginSuccessfully(String description, String email, String fullName) {
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

    @Test
    @DisplayName("Login with empty credentials")
    public void loginWithEmptyCredentials() {
        loginPage.login("");
        assertEquals("No email given", loginPage.getAlertMessage());
    }

}
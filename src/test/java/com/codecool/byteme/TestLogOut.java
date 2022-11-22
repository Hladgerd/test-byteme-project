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
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class TestLogOut {
    private LoginPage loginPage;
    private FeedPage feedPage;


    @BeforeEach
    void init() {
        loginPage = new LoginPage();
        feedPage = new FeedPage();
    }

    @AfterEach
    void close() {
        loginPage.closeWebDriver();
    }

    @Test
    @DisplayName("Logout successfully")
    public void logOutSuccessfully() {
        loginPage.login(Util.readProperty("email"));
        feedPage.logOutCurrentUser();
        loginPage.reOpenLoginPage();
        assertTrue(loginPage.isLoginButtonVisible());
    }

}
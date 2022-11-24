package com.codecool.byteme;

import com.codecool.byteme.pages.FeedPage;
import com.codecool.byteme.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(TestResultLoggerExtension.class)
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
    public void logOutSuccessfully() throws InterruptedException {
        loginPage.login();
        Thread.sleep(5000);
        feedPage.logOutCurrentUser();
        loginPage.navigateToProfilePage();
        assertTrue(loginPage.isLoginButtonVisible());
    }
}

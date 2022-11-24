package com.codecool.byteme;

import com.codecool.byteme.pages.FeedPage;
import com.codecool.byteme.pages.LoginPage;
import com.codecool.byteme.pages.ProfilePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(TestResultLoggerExtension.class)
public class TestSearch {
    LoginPage loginPage;
    FeedPage feedPage;
    ProfilePage profilePage;

    @BeforeEach
    void init() {
        loginPage = new LoginPage();
        profilePage = new ProfilePage();
        feedPage = new FeedPage();
        loginPage.login();
    }

    @AfterEach
    void close(){
        loginPage.closeWebDriver();
    }

    @ParameterizedTest
    @DisplayName("Search user")
    @ValueSource(strings = "Bacso Vanda")
    public void searchForUser(String name) {
        feedPage.searchFor(name);
        feedPage.selectTheFirstResult();
        assertEquals(name, profilePage.getFullName());
    }
}

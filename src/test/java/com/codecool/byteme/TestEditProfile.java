package com.codecool.byteme;

import com.codecool.byteme.pages.FeedPage;
import com.codecool.byteme.pages.LoginPage;
import com.codecool.byteme.pages.ProfilePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.security.auth.login.LoginContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestEditProfile {
    LoginPage loginPage;
    FeedPage feedPage;
    ProfilePage profilePage;

    @BeforeEach
    void init() {
        loginPage = new LoginPage();
        feedPage = new FeedPage();
        profilePage = new ProfilePage();
        loginPage.successfulLogin();
    }

    @AfterEach
    void close() {
        loginPage.closeWebDriver();
    }

    @ParameterizedTest
    @DisplayName("Edit Name")
    @ValueSource(strings = "oneZ scengreF")
    public void editName(String name){
        feedPage.openProfilePage();
        profilePage.openEditPanel();
        profilePage.editName(name);
        profilePage.saveChanges();
        assertEquals(name,profilePage.getFullName());
    }

    @ParameterizedTest
    @DisplayName("Edit Age")
    @ValueSource(strings = "130")
    public void editAge(String age){
        feedPage.openProfilePage();
        profilePage.openEditPanel();
        profilePage.editAge(age);
        profilePage.saveChanges();
        assertEquals(age,profilePage.getAge());
    }

}

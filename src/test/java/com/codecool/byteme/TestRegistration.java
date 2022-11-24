package com.codecool.byteme;

import com.codecool.byteme.pages.FeedPage;
import com.codecool.byteme.pages.LoginPage;
import com.codecool.byteme.pages.ProfilePage;
import com.codecool.byteme.pages.RegistrationPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.EmptySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(TestResultLoggerExtension.class)
public class TestRegistration {

    private LoginPage loginPage;
    private FeedPage feedPage;
    private ProfilePage profilePage;
    private RegistrationPage registrationPage;

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
    public void registerSuccessfully(String description, String fullName, String age, String password) {
        String email = Util.generateRandomString() + "@byte.me";
        registrationPage.registerUser(fullName, email, age, password);
        loginPage.login(email, password);
        assertEquals("http://localhost:3000/login", registrationPage.getCurrentUrl());
    }

    @ParameterizedTest
    @DisplayName("Register user with empty credentials")
    @EmptySource
    public void registerWithEmptyCredentials(String emptyText) {
        loginPage.openRegistrationForm();
        registrationPage.registerUser(emptyText, emptyText, emptyText, emptyText);

        assertEquals(registrationPage.getRegistrationUrl(), registrationPage.getCurrentUrl());
    }


    @ParameterizedTest
    @DisplayName("login user with wrong password")
    @CsvFileSource(resources = "/newUserCredentials.csv", numLinesToSkip = 1, delimiter = ';')
    public void registeredUserLogInWithWrongPassword(String description, String fullName, String age, String password) {
        String email = Util.generateRandomString() + "@byte.me";
        registrationPage.registerUser(fullName, email, age, password);
        loginPage.login(email, password + 'a');
        assertEquals("http://localhost:3000/login", registrationPage.getCurrentUrl());
    }

    @ParameterizedTest
    @DisplayName("login user with wrong email")
    @CsvFileSource(resources = "/newUserCredentials.csv", numLinesToSkip = 1, delimiter = ';')
    public void registeredUserLogInWithWrongEmail(String description, String fullName, String age, String password) {
        String email = Util.generateRandomString() + "@byte.me";
        registrationPage.registerUser(fullName, email, age, password);
        loginPage.login(email + "a", password);
        assertEquals("http://localhost:3000/login", registrationPage.getCurrentUrl());
    }

    @ParameterizedTest
    @DisplayName("Register user with existing credentials")
    @CsvFileSource(resources = "/newUserCredentials.csv", numLinesToSkip = 1, delimiter = ';')
    public void registerWithExistingCredentials(String description, String fullName, String age, String password) {
        String email = Util.generateRandomString() + "@byte.me";
        registrationPage.registerUser(fullName, email, age, password);
        registrationPage.registerUser(fullName, email, age, password);
        String actualMessage = "This email is already assigned to one of our users, please register with an other email address!";

        assertEquals(actualMessage, loginPage.getAlertMessage());
    }


    @ParameterizedTest
    @DisplayName("Register user with age in non-integer format")
    @CsvFileSource(resources = "/newUserCredentials.csv", numLinesToSkip = 1, delimiter = ';')
    public void registerWithNonIntegerAge(String description, String fullName, String age, String password) {
        String email = Util.generateRandomString() + "@byte.me";
        String nonIntegerAge = "age";
        registrationPage.registerUser(fullName, email, nonIntegerAge, password);
        loginPage.login(email, password);

        assertTrue(feedPage.isLogoutButtonVisible());
    }
}

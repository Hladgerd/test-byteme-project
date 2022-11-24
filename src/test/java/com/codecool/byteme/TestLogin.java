package com.codecool.byteme;

import com.codecool.byteme.pages.FeedPage;
import com.codecool.byteme.pages.LoginPage;
import com.codecool.byteme.pages.ProfilePage;
import com.codecool.byteme.pages.RegistrationPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(TestResultLoggerExtension.class)
public class TestLogin {
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
    @DisplayName("Login registered user with correct credentials")
    @CsvFileSource(resources = "/newUserCredentials.csv", numLinesToSkip = 1, delimiter = ';')
    public void loginSuccessfully(String description, String fullName, String age, String password) {
        String email = Util.generateRandomString() + "@byte.me";
        registrationPage.registerUser(fullName, email, age, password);
        loginPage.login(email, password);
        assertTrue(feedPage.isLogoutButtonVisible());

        feedPage.openProfilePage();
        assertEquals(fullName, profilePage.getFullName());
    }

    @ParameterizedTest
    @DisplayName("login registered user with wrong email")
    @CsvFileSource(resources = "/newUserCredentials.csv", numLinesToSkip = 1, delimiter = ';')
    public void registeredUserLogInWithWrongEmail(String description, String fullName, String age, String password) {
        String email = Util.generateRandomString() + "@byte.me";
        registrationPage.registerUser(fullName, email, age, password);
        loginPage.login(email + "a", password);
        assertFalse(feedPage.isLogoutButtonVisible());
    }

    @ParameterizedTest
    @DisplayName("login registered user with wrong password")
    @CsvFileSource(resources = "/newUserCredentials.csv", numLinesToSkip = 1, delimiter = ';')
    public void registeredUserLogInWithWrongPassword(String description, String fullName, String age, String password) {
        String email = Util.generateRandomString() + "@byte.me";
        registrationPage.registerUser(fullName, email, age, password);
        loginPage.login(email, password + 'a');
        assertFalse(feedPage.isLogoutButtonVisible());
    }

    @ParameterizedTest
    @DisplayName("Login user with empty credentials")
    @EmptySource
    public void loginWithEmptyCredentials(String emptyText) {
        loginPage.login(emptyText, emptyText);
        assertEquals("No email or password given!", loginPage.getAlertMessage());
    }

    @ParameterizedTest
    @DisplayName("Login unregistered user")
    @ValueSource(strings = "noname@byte.me")
    public void loginUnregisteredUser(String email) {
        loginPage.login(email, "pw");
        assertFalse(feedPage.isLogoutButtonVisible());
    }
}

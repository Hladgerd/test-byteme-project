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

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(TestResultLoggerExtension.class)
public class TestEditProfile {
    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private FeedPage feedPage;
    private ProfilePage profilePage;
    private String email;

    @BeforeEach
    void init() {
        registrationPage = new RegistrationPage();
        loginPage = new LoginPage();
        feedPage = new FeedPage();
        profilePage = new ProfilePage();
        email = Util.generateRandomString() + "@byte.me";
    }

    @AfterEach
    void close() {
        loginPage.closeWebDriver();
    }

    @ParameterizedTest
    @DisplayName("Edit Name")
    @CsvFileSource(resources = "/newUserCredentials.csv", numLinesToSkip = 1, delimiter = ';')
    public void editName(String description, String fullName, String age, String password) {
        registrationPage.registerUser(fullName, email, age, password);
        loginPage.login(email, password);

        String newName = "New name";
        feedPage.openProfilePage();
        profilePage.openEditPanel();
        profilePage.editName(newName);
        profilePage.saveChanges();
        assertEquals(newName, profilePage.getFullName());
    }

    @ParameterizedTest
    @DisplayName("Edit Age")
    @CsvFileSource(resources = "/newUserCredentials.csv", numLinesToSkip = 1, delimiter = ';')
    public void editAge(String description, String fullName, String age, String password) {
        registrationPage.registerUser(fullName, email, age, password);
        loginPage.login(email, password);

        String newAge = "100";
        feedPage.openProfilePage();
        profilePage.openEditPanel();
        profilePage.editAge(newAge);
        profilePage.saveChanges();
        assertEquals(newAge, profilePage.getAge());
    }
}

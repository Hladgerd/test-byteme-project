package com.codecool.byteme.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends BasePage {
    private final LoginPage loginPage = new LoginPage();
    private final String registrationUrl = baseUrl + "registration";

    @FindBy(id = "name-input-registration")
    WebElement registerName;

    @FindBy(id = "email-input-registration")
    WebElement registerEmail;

    @FindBy(id = "age-input-registration")
    WebElement registerAge;

    @FindBy(id = "password-input-registration")
    WebElement registerPassword;

    @FindBy(id = "registration-button")
    WebElement registerButton;

    private void fillOutForm(String fullName, String email, String age, String password) {
        registerName.sendKeys(fullName);
        registerEmail.sendKeys(email);
        registerAge.sendKeys(age);
        registerPassword.sendKeys(password);
    }

    public void submitRegistration() {
        registerButton.click();
    }

    public void registerUser(String fullName, String email, String age, String password){
        wait.until(ExpectedConditions.visibilityOf(registerName));
        fillOutForm(fullName, email, age, password);
        submitRegistration();
    }

    public String getRegistrationUrl() {
        return registrationUrl;
    }

    public String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }
}

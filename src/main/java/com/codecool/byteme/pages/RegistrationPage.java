package com.codecool.byteme.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends BasePage {

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


    public void fillOutForm(String fullName, String email, String age, String password) {
        registerName.sendKeys(fullName);
        registerEmail.sendKeys(email);
        registerAge.sendKeys(age);
        registerPassword.sendKeys(password);
    }

    public void fillOutForm_(String fullName) {
        registerName.sendKeys(fullName);
    }

    public String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }

    public void submitRegistration() {
        registerButton.click();
    }

    public String getRegistrationUrl() {
        return baseUrl + "registration";
    }

    public void openRegistrationForm() {
        webDriver.get(baseUrl + "registration");
        wait.until(ExpectedConditions.visibilityOf(registerName));
    }

}

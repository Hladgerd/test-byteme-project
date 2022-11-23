package com.codecool.byteme.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public void submitRegistration() { registerButton.click(); }

}

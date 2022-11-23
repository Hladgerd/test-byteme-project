package com.codecool.byteme.pages;

import com.codecool.byteme.Util;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    @FindBy(id = "email-input-login")
    WebElement userEmail;

    @FindBy(id = "password-input-login")
    WebElement userPassword;

    @FindBy(id = "byte-in-button")
    WebElement loginButton;

    @FindBy(id = "register-button")
    WebElement registrationButton;




    public void login(String email, String password){
        webDriver.get(baseUrl + "login");
        wait.until(ExpectedConditions.visibilityOf(userEmail));
        this.userEmail.sendKeys(email);
        this.userPassword.sendKeys("Pass"); // TODO: use parameter
        this.loginButton.click();
    }

    public void successfulLogin(){
        webDriver.get(baseUrl);
        wait.until(ExpectedConditions.visibilityOf(userEmail));
        String email = Util.readProperty("email");
        String password = "pass"; //TODO: read from prop file
        this.userEmail.sendKeys(email);
        this.userPassword.sendKeys(password);
        this.loginButton.click();
    }

    public String getAlertMessage() {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = webDriver.switchTo().alert();
        return alert.getText();
    }

    public boolean isLoginButtonVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(loginButton));
            return true;
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    public void reOpenLoginPage() {
        webDriver.get(baseUrl);
    }

    public void openRegistrationForm(){
    registrationButton.click(); }

    public void navigateToProfilePage() {
        webDriver.get(baseUrl + "user/2");
    }
}

package com.codecool.byteme.pages;

import com.codecool.byteme.Util;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    private final String loginUrl = baseUrl + "login";
    private final String registrationUrl = baseUrl + "registration";
    private final String userProfileUrl = baseUrl + "user/2";

    @FindBy(id = "email-input-login")
    WebElement userEmail;

    @FindBy(id = "password-input-login")
    WebElement userPassword;

    @FindBy(id = "byte-in-button")
    WebElement loginButton;

    public void openLoginPage() {
        webDriver.get(loginUrl);
        wait.until(ExpectedConditions.visibilityOf(userEmail));
    }

    public void login(String email, String password) {
        this.userEmail.sendKeys(email);
        this.userPassword.sendKeys(password);
        this.loginButton.submit();
    }

    public void login() {
        String email = Util.readProperty("email");
        String password = Util.readProperty("password");
        webDriver.get(loginUrl);
        wait.until(ExpectedConditions.visibilityOf(userEmail));
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

    public void openRegistrationForm() {
        webDriver.get(registrationUrl);
    }

    public void navigateToProfilePage() {
        webDriver.get(userProfileUrl);
    }
}

package com.codecool.byteme.pages;

import com.codecool.byteme.Util;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    @FindBy(xpath = "//*[@id=\"root\"]/div/form/div/input")
    WebElement userEmail;

    @FindBy(xpath = "//*[@id=\"root\"]/div/form/button")
    WebElement loginButton;

    public void login(String email){
        webDriver.get(baseUrl);
        wait.until(ExpectedConditions.visibilityOf(userEmail));
        this.userEmail.sendKeys(email);
        this.loginButton.click();
    }

    public void successfulLogin(){
        webDriver.get(baseUrl);
        wait.until(ExpectedConditions.visibilityOf(userEmail));
        String email = Util.readProperty("email");
        this.userEmail.sendKeys(email);
        this.loginButton.click();
    }
}

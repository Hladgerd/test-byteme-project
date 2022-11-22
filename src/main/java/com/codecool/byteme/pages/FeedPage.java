package com.codecool.byteme.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FeedPage extends BasePage {
    ProfilePage profilePage = new ProfilePage();

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/nav/div[3]/a")
    WebElement logoutButton;

    @FindBy(xpath = "//*[@id=\"navbarSupportedContent\"]/ul/li[1]/a")
    WebElement profilePageLink;

    public boolean isLogoutButtonVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(logoutButton));
            return true;
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    public void openProfilePage() {
        profilePageLink.click();
        wait.until(ExpectedConditions.visibilityOf(profilePage.fullName));
    }

    public void logOutCurrentUser() {
        logoutButton.click();
    }
}

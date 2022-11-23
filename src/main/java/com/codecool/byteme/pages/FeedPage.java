package com.codecool.byteme.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class FeedPage extends BasePage {
    ProfilePage profilePage = new ProfilePage();

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/nav/div[3]/a")
    WebElement logoutButton;

    @FindBy(xpath = "//*[@id=\"navbarSupportedContent\"]/ul/li[1]/a")
    WebElement profilePageLink;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[1]/form/div[1]/input")
    WebElement postTitle;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[1]/form/div[2]/textarea")
    WebElement postBody;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[1]/form/button")
    WebElement postButton;

    public boolean isLogoutButtonVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(logoutButton));
            return true;
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    public void openProfilePage(){
        profilePageLink.click();
        wait.until(ExpectedConditions.visibilityOf(profilePage.fullName));
    }

    public void logOutCurrentUser() {
        logoutButton.click();
    }

    public void fillTitle(String title) {
        postTitle.sendKeys(title);
    }
    public void fillBody(String body) {
        postBody.sendKeys(body);
    }
    public void savePost() {
        postButton.click();
    }
    public void createNewPost(String title, String body){
        fillTitle(title);
        fillBody(body);
        savePost();
    }
    public String getNewPostTitle(){
        return webDriver.findElement(By.className("post-title")).getText();
    }
    public String getNewPostBody(){
        return webDriver.findElement(By.className("post-body")).getText();
    }
    public void deleteNewPost(){
        webDriver.findElement(By.className("delete-icon")).click();
    }
}

package com.codecool.byteme.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FeedPage extends BasePage {
    ProfilePage profilePage = new ProfilePage();

    @FindBy(id = "logout")
    WebElement logoutButton;

    @FindBy(id = "user-page")
    WebElement profilePageLink;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[1]/form/div[1]/input")
    WebElement postTitle;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[1]/form/div[2]/textarea")
    WebElement postBody;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[1]/form/button")
    WebElement postButton;

    @FindBy(id = "search-text")
    WebElement searchField;

    public boolean isLogoutButtonVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(logoutButton));
            return true;
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    public void openProfilePage() {
        wait.until(ExpectedConditions.visibilityOf(profilePageLink));
        profilePageLink.click();
        wait.until(ExpectedConditions.visibilityOf(profilePage.fullName));
    }

    public void logOutCurrentUser() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.click();
    }

    public void createNewPost(String title, String body) {
        int numberOfPosts = (webDriver.findElements(By.className("post-card"))).size();
        postTitle.sendKeys(title);
        postBody.sendKeys(body);
        postButton.click();
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("post-card"), numberOfPosts));
    }

    public String getNewlyCreatedPostTitle(String title) {
        wait.until(ExpectedConditions.textToBePresentInElement(webDriver.findElement(By.className("post-title")), title));
        WebElement titleElement = webDriver.findElement(By.className("post-title"));
        return titleElement.getText();
    }

    public String getLatestPostTitle() {
        return webDriver.findElement(By.className("post-title")).getText();
    }

    public String getNewlyCreatedPostBody(String body) {
        WebElement bodyElement = webDriver.findElement(By.className("post-body"));
        wait.until(ExpectedConditions.textToBePresentInElement(bodyElement, body));
        return bodyElement.getText();
    }

    public String getLatestPostBody() {
        return webDriver.findElement(By.className("post-body")).getText();
    }

    public void deleteNewPost() {
        int numberOfPosts = (webDriver.findElements(By.className("post-card"))).size();
        webDriver.findElement(By.className("delete-icon")).click();
        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.className("post-card"), numberOfPosts));
    }

    public void searchFor(String name) {
        searchField.sendKeys(name);
    }

    public void selectTheFirstResult() {
        webDriver.findElement(By.className("searchbar-results-item")).click();
    }
}

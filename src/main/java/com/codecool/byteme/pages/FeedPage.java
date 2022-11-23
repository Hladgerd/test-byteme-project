package com.codecool.byteme.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FeedPage extends BasePage {
    private final ProfilePage profilePage = new ProfilePage();

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
        logoutButton.click();
    }

    public void openFeeds() {
        wait.until(ExpectedConditions.visibilityOf(postTitle));
    }

    public void createNewPost(String title, String body) {
        int numberOfPosts = (webDriver.findElements(By.className("post-card"))).size();
        postTitle.sendKeys(title);
        postBody.sendKeys(body);
        postButton.click();
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("post-card"), numberOfPosts));
    }

    private WebElement findNewTitle(String title) {
        WebElement titleElement = webDriver.findElement(By.className("post-title"));
        wait.until(ExpectedConditions.textToBePresentInElement(titleElement, title));
        return titleElement;
    }

    private WebElement findNewBody(String body) {
        WebElement bodyElement = webDriver.findElement(By.className("post-body"));
        wait.until(ExpectedConditions.textToBePresentInElement(bodyElement, body));
        return bodyElement;
    }

    public String getNewlyCreatedPostTitle(String title) {
        return findNewTitle(title).getText();
    }

    public String getLatestPostTitle() {
        return webDriver.findElement(By.className("post-title")).getText();
    }

    public String getNewlyCreatedPostBody(String body) {
        return findNewBody(body).getText();
    }

    public String getLatestPostBody() {
        return webDriver.findElement(By.className("post-body")).getText();
    }

    public void deleteNewPost(String title) {
//        findNewTitle(title);
        webDriver.findElement(By.className("delete-icon")).click();
    }

    public void searchFor(String name) {
        searchField.sendKeys(name);
    }

    public void selectTheFirstResult() {
        webDriver.findElement(By.className("searchbar-results-item")).click();
    }
}

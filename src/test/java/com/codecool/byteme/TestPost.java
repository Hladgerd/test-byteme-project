package com.codecool.byteme;

import com.codecool.byteme.pages.FeedPage;
import com.codecool.byteme.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestPost {
    LoginPage loginPage;
    FeedPage feedPage;
    boolean shouldBeDeleted;

    @BeforeEach
    void init() {
        loginPage = new LoginPage();
        feedPage = new FeedPage();
        loginPage.login();
        shouldBeDeleted = false;
    }

    @AfterEach
    void close() {
        if (shouldBeDeleted) feedPage.deleteNewPost();
        loginPage.closeWebDriver();
    }

    @Test
    @DisplayName("Add post successfully")
    public void addPostSuccessfully() {
        shouldBeDeleted = true;
        String title = Util.generateRandomString();
        String body = Util.generateRandomString();
        feedPage.createNewPost(title, body);

        assertEquals(title, feedPage.getNewlyCreatedPostTitle(title));
        assertEquals(body, feedPage.getNewlyCreatedPostBody(body));
    }

    @Test
    @DisplayName("Delete Post successfully")
    public void deletePostSuccessfully() {
        String title = Util.generateRandomString();
        String body = Util.generateRandomString();
        feedPage.createNewPost(title, body);
        feedPage.deleteNewPost();

        assertNotEquals(title, feedPage.getLatestPostTitle());
        assertNotEquals(body, feedPage.getLatestPostBody());
    }
}

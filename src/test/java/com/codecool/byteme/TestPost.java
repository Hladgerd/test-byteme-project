package com.codecool.byteme;

import com.codecool.byteme.pages.FeedPage;
import com.codecool.byteme.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPost {
    LoginPage loginPage;
    FeedPage feedPage;

    @BeforeEach
    void init() {
        loginPage = new LoginPage();
        feedPage = new FeedPage();
        loginPage.successfulLogin();
    }

    @AfterEach
    void close() {
        loginPage.closeWebDriver();
    }

    @Test
    @DisplayName("Add post successfully")
    public void addPostSuccessfully(){
        String title = Util.generateRandomString();
        String body = Util.generateRandomString();
        feedPage.createNewPost(title, body);

        assertEquals(title,feedPage.getNewPostTitle());
        assertEquals(body,feedPage.getNewPostBody());
        feedPage.deleteNewPost();
    }


}

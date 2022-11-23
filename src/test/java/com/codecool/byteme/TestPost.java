package com.codecool.byteme;

import com.codecool.byteme.pages.FeedPage;
import com.codecool.byteme.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPost {
    LoginPage loginPage;
    FeedPage feedPage;
    boolean isdeletable;

    @BeforeEach
    void init() {
        loginPage = new LoginPage();
        feedPage = new FeedPage();
        loginPage.login();
        isdeletable = false;
    }

    @AfterEach
    void close() {
        if(isdeletable) feedPage.deleteNewPost();
        loginPage.closeWebDriver();
    }

    @Test
    @DisplayName("Add post successfully")
    public void addPostSuccessfully(){
        isdeletable = true;
        String title = Util.generateRandomString();
        String body = Util.generateRandomString();
        feedPage.createNewPost(title, body);

        assertEquals(title,feedPage.getNewlyCreatedPostTitle(title));
        assertEquals(body,feedPage.getNewlyCreatedPostBody(body));
    }

    @Test
    @DisplayName("Delete Post successfully")
    public void deletePostSuccessfully(){
        String title = Util.generateRandomString();
        String body = Util.generateRandomString();
        feedPage.createNewPost(title, body);
        feedPage.deleteNewPost();

        assertNotEquals(title,feedPage.getLatestPostTitle());
        assertNotEquals(body,feedPage.getLatestPostBody());
    }


}

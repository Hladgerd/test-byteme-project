package com.codecool.byteme.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends BasePage {
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[1]/div[2]/div[1]")
    WebElement fullName;

    public String getFullName() {
        String[] result = fullName.getText().split(" ", 2);
        return result.length > 1 ? result[1] : "";
    }
}

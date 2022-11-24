package com.codecool.byteme.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends BasePage {
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[1]/div[2]/div[1]/div[1]")
    WebElement fullName;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[1]/div[2]/div[1]/div[2]")
    WebElement age;

    @FindBy(id = "myBtn")
    WebElement editButton;

    @FindBy(xpath = "//*[@id=\"user-name\"]/div/button")
    WebElement panelNameEditButton;

    @FindBy(xpath = "//*[@id=\"user-age\"]/div/button")
    WebElement panelAgeEditButton;

    @FindBy(xpath = "//*[@id=\"user-name\"]/p[2]/input")
    WebElement panelNameInput;

    @FindBy(xpath = "//*[@id=\"user-age\"]/div/button")
    WebElement panelAgeInput;

    @FindBy(xpath = "//*[@id=\"myModal\"]/div/div[3]/button[2]")
    WebElement panelSaveChangesButton;

    public String getFullName(){
        String[] result = fullName.getText().split(" ",2);
        return result.length > 1 ? result[1] : "";
    }

    public void openEditPanel() {
        editButton.click();
        wait.until(ExpectedConditions.visibilityOf(panelNameEditButton));
    }

    public void editName(String name) {
        panelNameEditButton.click();
        panelNameInput.sendKeys(name);
        panelNameEditButton.click();
    }

    public void saveChanges(){
        panelSaveChangesButton.click();
    }

    public void editAge(String age) {
        panelAgeEditButton.click();
        panelAgeInput.sendKeys(age);
        panelAgeEditButton.click();
    }

    public String getAge() {
        String[] result = age.getText().split(" ",2);
        return result.length > 1 ? result[1] : "";
    }
}

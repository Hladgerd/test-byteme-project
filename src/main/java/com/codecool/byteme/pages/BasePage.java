package com.codecool.byteme.pages;

import com.codecool.byteme.Util;
import com.codecool.byteme.WebdriverUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class BasePage {

    protected WebDriver webDriver;
    protected WebDriverWait wait;

    protected String baseUrl = Util.readProperty("baseUrl");
    private final WebdriverUtil webdriverUtil;


    public BasePage() {
        webdriverUtil = WebdriverUtil.getInstance();
        this.webDriver = webdriverUtil.getWebDriver();
        wait = initWebdriverWait();
        PageFactory.initElements(webDriver, this);
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    private WebDriverWait initWebdriverWait() {
        return webdriverUtil.getWebDriverWait();
    }

    public void closeWebDriver() {
        webdriverUtil.shutDown();
    }
}

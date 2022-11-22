package com.codecool.byteme.pages;

import com.codecool.byteme.Util;
import com.codecool.byteme.WebdriverUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class BasePage {

    protected WebDriver webDriver;
    protected WebDriverWait wait;
    private final WebdriverUtil webdriverUtil;
    protected String baseUrl = Util.readProperty("baseUrl");

    public BasePage() {
        webdriverUtil = WebdriverUtil.getInstance();
        this.webDriver = webdriverUtil.getWebDriver();
        wait = initWebdriverWait();
        PageFactory.initElements(webDriver, this);
    }

    private WebDriverWait initWebdriverWait() {
        return webdriverUtil.getWebDriverWait();
    }

    public void closeWebDriver() {
        webdriverUtil.shutDown();
    }
}

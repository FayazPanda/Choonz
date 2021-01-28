package com.selenium.pages.demosite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateGenre {
    private WebDriver driver;

    @FindBy(id = "title")
    private WebElement title;

    @FindBy(id = "desc")
    private WebElement desc;

    @FindBy(id = "submit")
    private WebElement submit;

    public CreateGenre(WebDriver driver) {
        super();
        this.driver = driver;
        PageFactory.initElements(driver, this);
        new WebDriverWait(driver, 3);
    }

    // METHODS
    public void create() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(title));
        title.sendKeys("Test Genre");
        desc.sendKeys("Desc");
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(submit));
        submit.click();
    }
}

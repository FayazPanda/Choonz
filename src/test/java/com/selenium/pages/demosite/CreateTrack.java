package com.selenium.pages.demosite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateTrack {
    private WebDriver driver;

    @FindBy(id = "track-name")
    private WebElement name;

    @FindBy(id = "duration")
    private WebElement duration;

    @FindBy(id = "lyrics")
    private WebElement lyrics;

    @FindBy(id = "saveTrackBtn")
    private WebElement submit;

    public CreateTrack(WebDriver driver) {
        super();
        this.driver = driver;
        PageFactory.initElements(driver, this);
        new WebDriverWait(driver, 3);
    }

    // METHODS
    public void create() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(name));
        name.sendKeys("Test Track");
        duration.sendKeys("150");
        lyrics.sendKeys("These Are Lyrics");
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(submit));
        submit.click();
    }
}

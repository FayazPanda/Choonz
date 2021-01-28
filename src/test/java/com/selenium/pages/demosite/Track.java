package com.selenium.pages.demosite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Track {
    private WebDriver driver;

    @FindBy(id = "trackName")
    private WebElement trackName;

    @FindBy(id = "edit")
    private WebElement trackEdit;

    @FindBy(id = "track-name")
    private WebElement trackEditName;

    @FindBy(id = "saveEditBtn")
    private WebElement save;

    @FindBy(id = "delete")
    private WebElement delete;

    // CONSTRUCTOR
    public Track(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        new WebDriverWait(driver, 3);
    }

    public WebElement getTrackName() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(trackName));
        return trackName;
    }

    public void editTrack() throws InterruptedException {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(trackEdit));
        trackEdit.click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(trackEditName));
        trackEditName.sendKeys("Test");
        save.click();
    }

    public void delete() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(delete));
        delete.click();
    }


}

package com.selenium.pages.demosite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreatePlaylist {
    // ATTRIBUTES
    public static final String URL = "localhost:8082";
    private WebDriver driver;

    @FindBy(id = "name")
    private WebElement name;

    @FindBy(id = "desc")
    private WebElement desc;

    @FindBy(id = "art")
    private WebElement art;

    @FindBy(id = "createPlaylist")
    private WebElement createPlaylist;


    // CONSTRUCTOR
    public CreatePlaylist(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        new WebDriverWait(driver, 3);
    }

    // METHODS
    public void create() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(name));
        name.sendKeys("Test Playlist");
        desc.sendKeys("Description Here");
        art.sendKeys("https://upload.wikimedia.org/wikipedia/en/4/42/Beatles_-_Abbey_Road.jpg");
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(createPlaylist));
        createPlaylist.click();
    }
}

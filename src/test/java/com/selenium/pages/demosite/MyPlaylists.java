package com.selenium.pages.demosite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyPlaylists {
    // ATTRIBUTES
    public static final String URL = "localhost:8082";
    private WebDriver driver;

    @FindBy(xpath = "/html/body/div/div/div[1]/a")
    private WebElement playlist;
    @FindBy(id = "playlistTitle")
    private WebElement playlistName;

    @FindBy(xpath = "/html/body/div/div/div[4]/a")
    private WebElement testplaylist;
    @FindBy(id = "playlistTitle")
    private WebElement testPlaylistName;
    @FindBy(xpath = "/html/body/div/div/div[1]/a/h3")
    private WebElement name;

    @FindBy(id = "all")
    private WebElement all;


    // CONSTRUCTOR
    public MyPlaylists(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        new WebDriverWait(driver, 3);
    }

    public void clickPlaylist() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(playlist));
        playlist.click();
    }

    public void clickTestPlaylist() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(testplaylist));
        testplaylist.click();
    }

    public WebElement getPlaylistName() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(playlistName));
        return playlistName;
    }


    public WebElement getAll() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(all));
        return all;
    }

    public WebElement getTestPlaylistName() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(testPlaylistName));
        return testPlaylistName;
    }

    public WebElement getName() {
        return name;
    }


}

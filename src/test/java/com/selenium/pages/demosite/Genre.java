package com.selenium.pages.demosite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Genre {
    private WebDriver driver;

    @FindBy(id = "genreName")
    private WebElement genreTitle;

    @FindBy(id = "edit")
    private WebElement genreEdit;

    @FindBy(id = "genre-title")
    private WebElement genreEditName;

    @FindBy(id = "desc")
    private WebElement desc;

    @FindBy(id = "saveEditBtn")
    private WebElement save;

    // CONSTRUCTOR
    public Genre(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        new WebDriverWait(driver, 3);
    }

    public WebElement getGenreTitle() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(genreTitle));
        return genreTitle;
    }

    public void editGenre() throws InterruptedException {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(genreEdit));
        genreEdit.click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(genreEditName));
        genreEditName.sendKeys("Test");
        desc.sendKeys("Test");
        save.click();
    }


}

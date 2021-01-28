package com.selenium.pages.demosite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateAlbum {
    private WebDriver driver;

	@FindBy(id = "name")
	private WebElement name;
	
	@FindBy(id = "art")
	private WebElement art;
	
	@FindBy(id = "submit")
	private WebElement submit;
	public CreateAlbum(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
		new WebDriverWait(driver, 3);
	}
	// METHODS
		public void create() {
			new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(name));
			name.sendKeys("Test Album");
			art.sendKeys("https://upload.wikimedia.org/wikipedia/en/4/42/Beatles_-_Abbey_Road.jpg");
			new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(submit));
			submit.click();
		}
}

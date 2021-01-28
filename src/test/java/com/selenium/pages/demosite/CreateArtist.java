package com.selenium.pages.demosite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateArtist {
    private WebDriver driver;

	@FindBy(id = "name")
	private WebElement name;


	
	@FindBy(id = "submit")
	private WebElement submit;
	public CreateArtist(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
		new WebDriverWait(driver, 3);
	}
	// METHODS
		public void create() {
			new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(name));
			name.sendKeys("Test Artist");
			new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(submit));
			submit.click();
		}
}

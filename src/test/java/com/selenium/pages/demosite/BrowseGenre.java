package com.selenium.pages.demosite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowseGenre {
    private WebDriver driver;

	@FindBy(xpath = "/html/body/div/div/div[1]/a")
	private WebElement pop;

	
	public BrowseGenre(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
		new WebDriverWait(driver, 3);
	}
	// METHODS
		public void clickPop() {
			new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(pop));
	
			pop.click();
		}
}

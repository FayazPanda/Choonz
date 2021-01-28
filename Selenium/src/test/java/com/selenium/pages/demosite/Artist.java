package com.selenium.pages.demosite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Artist {
	 private WebDriver driver;
	 
		@FindBy(id = "artistName")
		private WebElement artistName;
		
		@FindBy(id = "edit")
		private WebElement artistEdit;
		
		@FindBy(id = "arist-name")
		private WebElement artistEditName;
		
		@FindBy(id = "saveEditBtn")
		private WebElement save;
		
		@FindBy(id = "delete")
		private WebElement delete;
		
		@FindBy(id = "allAlbums")
		private WebElement gallery;
		// CONSTRUCTOR
		public Artist(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
			new WebDriverWait(driver, 3);
		}
		public WebElement getArtistName() {
			new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(artistName));
			return artistName;
		}
	
		public void editArtist() throws InterruptedException {
			new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(artistEdit));
			artistEdit.click();
			new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(artistEditName));
			artistEditName.sendKeys("Test");
			new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(save));
			save.click();
		}

public WebElement getGallery() {
			return gallery;
		}

public void delete() {
	new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(delete));
	delete.click();
}

		
}

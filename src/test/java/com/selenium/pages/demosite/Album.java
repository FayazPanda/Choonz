package com.selenium.pages.demosite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Album {
	 private WebDriver driver;
	 
		@FindBy(id = "albumTitle")
		private WebElement albumTitle;
		
		@FindBy(xpath="/html/body/main/div/div[1]/div/div[2]/a")
		private WebElement albumArtist;
		
		@FindBy(xpath="/html/body/main/div/div[3]/a")
		private WebElement albumTrack;
		
		@FindBy(id = "edit")
		private WebElement albumEdit;
		
		@FindBy(id = "album-title")
		private WebElement albumEditTitle;
		
		@FindBy(id = "delete")
		private WebElement delete;
		
		@FindBy(id = "saveEditBtn")
		private WebElement save;
		
		@FindBy(id = "popAlbums")
		private WebElement popAlbums;
		
		@FindBy(id = "table")
		private WebElement wrapper;
		
		@FindBy(xpath = "/html/body/main/div/button")
		private WebElement addTrack;
		
		@FindBy(xpath = "/html/body/main/div/div[4]/button")
		private WebElement addToPlaylist;
		
		@FindBy(id = "addTrack")
		private WebElement addToPlaylistBtn;
		// CONSTRUCTOR
		public Album(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
			new WebDriverWait(driver, 3);
		}
		public WebElement getAlbumTitle() {
			new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(albumTitle));
			return albumTitle;
		}
		
		public void addToPlaylist() {
			new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(addToPlaylist));
			addToPlaylist.click();
			new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(addToPlaylistBtn));
			addToPlaylistBtn.click();
		}
		public void addTrack() {
			new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(addTrack));
			addTrack.click();
			
		}
		public WebElement getWrapper() {
			return wrapper;
		}

		public WebElement getPopAlbums() {
			return popAlbums;
		}
		public void clickArtist() {
			new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(albumArtist));
			albumArtist.click();
		}
		public void clickTrack() {
			new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(albumTrack));
			albumTrack.click();
		}

		public void clickAlbumEdit() throws InterruptedException {
			new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(albumEdit));
			albumEdit.click();
			//Thread.sleep(3000);
			new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(albumEditTitle));
			albumEditTitle.sendKeys("Test");
			new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(save));
			save.click();
			
		}
		
		public void deleteAlbum() {
			new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(delete));
			delete.click();
		}
	
		
}

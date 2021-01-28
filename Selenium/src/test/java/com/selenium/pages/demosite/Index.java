package com.selenium.pages.demosite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Index {
	// ATTRIBUTES
	public static final String URL = "localhost:8082";
	public static String GenreURL = "http://localhost:8082/browse.html?page=genres";
	public static String AlbumURL = "http://localhost:8082/browse.html?page=albums";
	public static String ArtistURL = "http://localhost:8082/browse.html?page=artists";
	public static String AllPlaylistURL = "http://localhost:8082/browse.html?page=playlists";
	public static String MyPlaylistsURL = "http://localhost:8082/browse.html?page=myplaylists";
	public static String CreatePlaylistURL = "http://localhost:8082/createPlaylist.html";
    private WebDriver driver;
    
	@FindBy(xpath = "/html/body/nav/div/ul[2]/li[2]/div/a")
	private WebElement logIn;

	@FindBy(id = "welcomeMessage")
	private WebElement welcomeMessage;
	
	
	@FindBy(xpath = "/html/body/main/div/div[2]/div[1]/a")
	private WebElement topAlbum;
	
	@FindBy(xpath = "/html/body/nav/div/ul[1]/li[1]/a")
	private WebElement genres;
	
	@FindBy(xpath = "/html/body/nav/div/ul[1]/li[2]/a")
	private WebElement albums;
	
	@FindBy(xpath = "/html/body/nav/div/ul[1]/li[3]/a")
	private WebElement artists;

	@FindBy(xpath = "/html/body/div/div/div[1]/a/h3")
	private WebElement topGenre;
	
	@FindBy(xpath = "/html/body/div/div/div[1]/a/h3")
	private WebElement browserAlbum;
	
	@FindBy(xpath = "/html/body/div/div/div[1]/a")
	private WebElement browserArtist;
	
	@FindBy(xpath = "/html/body/div/div/div[1]/a")
	private WebElement browserPlaylist;
	
	@FindBy(name = "searchQuery")
	private WebElement search;
	
	@FindBy(id = "resultAlbums")
	private WebElement searchResult;
	
	
	// CONSTRUCTOR
	public Index(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		new WebDriverWait(driver, 3);
		//logInPage = new LogInPage(driver);
		
		

	}
	
	// METHODS
	public void navLogIn() {
		 new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(logIn));
		logIn.click();
		
	}

	public WebElement getBrowserArtist() {
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(browserArtist));
		return browserArtist;
	}


	public WebElement getBrowserPlaylist() {
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(browserPlaylist));
		return browserPlaylist;
	}


	public void search() {
		 new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(search));
		search.sendKeys("internet\r\n");
		
	}
	

	public WebElement getSearchResult() {
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(searchResult));
		return searchResult;
	}


	public WebElement getBrowserAlbum() {
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(browserAlbum));
		return browserAlbum;
	}

	public String returnMessage() {
		 new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOf(welcomeMessage));
		return welcomeMessage.getText();
	}

	
	public WebElement getTopGenre() {
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(topGenre));
		return topGenre;
	}

	public void clickGenres() {
		 new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(genres));
		genres.click();
	}
	public void clickAlbums() {
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(albums));
		albums.click();
	}
	public void clickArtists() {
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(artists));
		artists.click();
	}
	public void clickTopAlbum() {
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(topAlbum));
		topAlbum.click();
	}

	
	
	public String returnTopAlbum() {
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(topAlbum));
		return topAlbum.getText();
	}
}

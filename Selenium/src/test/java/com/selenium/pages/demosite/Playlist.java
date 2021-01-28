package com.selenium.pages.demosite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Playlist {
	// ATTRIBUTES
	public static final String URL = "localhost:8082";
    private WebDriver driver;
    
	@FindBy(xpath = "/html/body/div/div/div[1]/a")
	private WebElement name;

	@FindBy(id = "delete")
	private WebElement delete;
	
	@FindBy(id = "saveEditBtn")
	private WebElement save;
	
	@FindBy(id = "table")
	private WebElement table;
	
	@FindBy(xpath = "/html/body/main/div/div[3]/button")
	private WebElement deleteFrom;
	
	@FindBy(id = "confirmDelete")
	private WebElement deleteFrombtn;
	// CONSTRUCTOR
	public Playlist(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		new WebDriverWait(driver, 3);
	}

	public WebElement getName() {
		return name;
	}

	public void delete() {
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(delete));
		delete.click();
	}
	
	public void deleteFrom() {
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(deleteFrom));
		deleteFrom.click();
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(deleteFrombtn));
		deleteFrombtn.click();
	
	}
	public void deleteFrombtn() {
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(deleteFrombtn));
		deleteFrombtn.click();
	}
	public WebElement getTable() {
		return table;
	}



	



}

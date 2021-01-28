package com.selenium.pages.demosite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogInPage {
	// ATTRIBUTES
	public static final String URL = "localhost:8082";
    private WebDriver driver;
	@FindBy(id = "logInName")
	private WebElement usernameLogIn;
	
	@FindBy(id = "logInPassword")
	private WebElement passwordLogIn;
	
	@FindBy(id = "logInButton")
	private WebElement logInButton;
	
	@FindBy(id = "signUpName")
	private WebElement usernameRegister;
	
	@FindBy(id = "signUpPassword1")
	private WebElement passwordRegister;
	
	@FindBy(id = "signUpPassword2")
	private WebElement passwordRegister2;
	
	@FindBy(id = "registerButton")
	private WebElement registerButton;
	// CONSTRUCTOR
	public LogInPage(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(driver, this);
		new WebDriverWait(driver, 3);
		
	}
	
	
	// METHODS
	public void logIn() {
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(usernameLogIn));
		usernameLogIn.sendKeys("test");
		passwordLogIn.sendKeys("test");
		logInButton.click();
	}
	
	public void logInAdmin() {
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(usernameLogIn));
		usernameLogIn.sendKeys("admin");
		passwordLogIn.sendKeys("test");
		logInButton.click();
	}
	public void register() {
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(usernameRegister));
		usernameRegister.sendKeys("testy");
		passwordRegister.sendKeys("testy");
		passwordRegister2.sendKeys("testy");
		registerButton.click();
	}

}

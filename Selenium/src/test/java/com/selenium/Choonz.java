package com.selenium;






import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;



import com.selenium.pages.demosite.Index;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import org.junit.runners.MethodSorters;
@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/cuke",plugin = {"pretty",
"html:src/test/resources/reports/htmlReports" }, monochrome=true,glue = {"stepdef"})
public class Choonz {

}
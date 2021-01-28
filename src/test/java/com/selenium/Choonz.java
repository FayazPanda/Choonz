package com.selenium;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/cuke", plugin = {"pretty",
        "html:src/test/resources/reports/htmlReports"}, monochrome = true, glue = {"stepdef"})
public class Choonz {

}
package com.company.sampleapp.tests;

import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.company.sampleapp.pages.SearchPage;
import com.company.sampleapp.utils.TestBase;


public class SearchTest extends TestBase {
	
	
	 @BeforeTest
	  public void Setup()  {
		 driver.manage().deleteAllCookies();
		 driver.manage().window().maximize();
		 driver.get("http://google.com");
		 
	 }
	
  @Test
  public void SearchGoogleTest() throws InterruptedException {
	 String searchData ="Star Trek";
	 
	  SearchPage searchPage = new SearchPage(driver);
	  
	  //action
	  searchPage.Search(searchData);
	  Reporter.log("Searching Google for Star Wars");
	  //Add this is core class
	 
	 
	  
	  //assertion
	
	  assertTrue(driver.getPageSource().contains(searchData));
	  assertTrue("Failed to find div resultsStats",driver.getPageSource().contains("resultStats"));
	  
	  
  }
  
  


}

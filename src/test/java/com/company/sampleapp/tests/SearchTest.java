package com.company.sampleapp.tests;

import static org.testng.AssertJUnit.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.company.sampleapp.pages.SearchPage;
import com.company.sampleapp.utils.TestBase;


public class SearchTest extends TestBase {
	private static Logger LOGGER = Logger.getLogger(SearchTest.class);
	
	 @BeforeTest
	  public void Setup()  {
		 driver.manage().deleteAllCookies();
		 driver.manage().window().maximize();
		 driver.get("http://google.com");
		 
	 }
	
  @Test ( dataProvider = "SearchTest")
  public void SearchGoogleTest(String searchData, String expectedResults) throws InterruptedException {
	 //String searchData ="Star Trek";
	 
	  SearchPage searchPage = new SearchPage(driver);
	  
	  //action
	  searchPage.Search(searchData);
	  Reporter.log("Searching Google for Star Wars");
	  //Add this is core class
	 
	 
	  
	  //assertion
	
	  assertTrue(driver.getPageSource().contains(searchData));
	  assertTrue("Failed to find div resultsStats",driver.getPageSource().contains(expectedResults));
	  
	  
  }
  

	@DataProvider
	public Object[][] SearchTest() {
		Object[][] data = new Object[][] { 
				new Object[] { "Star Trek","Star Trek"},
				new Object[] { "Star War","Star War"},
				new Object[] { "Superman","Star War"}
			
				
			};
		return data;
	}


}

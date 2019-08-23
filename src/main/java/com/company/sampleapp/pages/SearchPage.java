package com.company.sampleapp.pages;




import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;


public class SearchPage {
	WebDriverWait wait;
	private static Logger LOGGER = Logger.getLogger(SearchPage.class);

	public SearchPage(WebDriver driver) {
		
		 
		wait = new WebDriverWait(driver, 30);
		if (!driver.getTitle().equals("Google")) {
			LOGGER.error("Page navigation is not valid");			

		} else {
			LOGGER.info("Current URL: " + driver.getCurrentUrl());
		}
		
	    PageFactory.initElements(driver, this);
		
	}
	

	
	//declare page elements
	@FindBy(how=How.NAME, using="q")
	private WebElement txtSearchTerm;
	private WebElement btnG;

	private WebElement resultStats;
	
	public SearchPage Search(String strSearchTerm){	
		wait.until(ExpectedConditions.elementToBeClickable(txtSearchTerm));
		txtSearchTerm.clear();
		txtSearchTerm.sendKeys(strSearchTerm);
		wait.until(ExpectedConditions.elementToBeClickable(btnG));
		txtSearchTerm.sendKeys(Keys.ENTER);
		//wait.until(ExpectedConditions.elementToBeClickable(btnG));
		//btnG.click();
		return this;		
		
	}




}

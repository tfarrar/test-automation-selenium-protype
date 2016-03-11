package com.company.sampleapp.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
 
public class WebDriverListener implements WebDriverEventListener {
	private static Logger LOGGER = Logger.getLogger( WebDriverListener.class );
	private WebDriver webDriver;
	
	public WebDriverListener(WebDriver webDriver){
		this.webDriver = webDriver;
	}
 
	public void beforeNavigateTo(String url, WebDriver driver) {
		LOGGER.info("Before navigating to "+url);
		
 
	}
 
	public void afterNavigateTo(String url, WebDriver driver) {
		LOGGER.info("after navigating to "+url+" the title is "+driver.getTitle());
 
	}
 
	public void beforeNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub
 
	}
 
	public void afterNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub
 
	}
 
	public void beforeNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub
 
	}
 
	public void afterNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub
 
	}
 
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
 
	}
 
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
 
	}
 
	public void beforeClickOn(WebElement element, WebDriver driver) {
		//TODO
 
	}
 
	public void afterClickOn(WebElement element, WebDriver driver) {
		LOGGER.info("After Clicking Url "+driver.getCurrentUrl());
 
	}
 
	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
 
	}
 
	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
 
	}
 
	public void beforeScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
 
	}
 
	public void afterScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
 
	}
 

	public void onException(Throwable throwable, WebDriver driver) {
		LOGGER.error("There is an exception in the script, please find the below error description" );

		throwable.printStackTrace();
 
	}
 
}

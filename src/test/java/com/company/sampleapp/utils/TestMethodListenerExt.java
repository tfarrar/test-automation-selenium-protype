package com.company.sampleapp.utils;

import java.io.File;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;



public class TestMethodListenerExt implements ISuiteListener, ITestListener {
	private static Logger LOGGER = Logger.getLogger(TestMethodListenerExt.class);

	private String cycleID = null;

	public void onTestStart(ITestResult tr) {

		LOGGER.info(tr.getName() + "--Test Method starting... \n");


	}

	public void onTestFailure(ITestResult tr) {
		LOGGER.error(tr.getName() + "--Test method failed\n");
		this.getScreenshot(tr);


	}

	public void onTestSkipped(ITestResult tr) {
		LOGGER.error(tr.getName() + " Test method blocked\n");
	}

	public void onTestSuccess(ITestResult tr) {
		LOGGER.info(tr.getName() + "--Test method passed\n");
		this.getScreenshot(tr);
	}

	@AfterMethod
	public void tearDown(ITestResult testResult) {
		Object currentClass = testResult.getInstance();
		WebDriver driver = ((TestBase) currentClass).getDriver();
		if (driver != null) {
			try {
				driver.quit();
			} catch (WebDriverException e) {
				System.out.println("***** CAUGHT EXCEPTION IN DRIVER TEARDOWN *****");
				System.out.println(e);
			}

		}

	}

	public void getScreenshot(ITestResult testResult) {

		Object currentClass = testResult.getInstance();
		WebDriver driver = ((TestBase) currentClass).getDriver();
		// WebDriver driver = LocalDriverManager.getDriver();
		// WebDriver driver = this.driver;
		boolean always = true;
		String testStatus = "failed";
		if (testResult.isSuccess()) {
			testStatus = "passed";

		}

		try {
			String screenShotsFolder = "target/surefire-reports/html/";
			String testImgDir = "";
			// Create a calendar object so we can create a date and time for the
			// screenshot
			Calendar calendar = Calendar.getInstance();
			// The file includes the the test method and the test class
			String testMethodAndTestClass = testStatus + "-" + testResult.getMethod().getMethodName() + "(" + testResult.getTestClass().getName() + ")";

			LOGGER.info(" *** This is where the capture file is created for the Test \n" + testMethodAndTestClass);
			// Create the filename for the screen shots
			String filename = testMethodAndTestClass + "-" + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH)
					+ "-" + calendar.get(Calendar.HOUR_OF_DAY) + "-" + calendar.get(Calendar.MINUTE) + "-" + calendar.get(Calendar.SECOND) + "-"
					+ calendar.get(Calendar.MILLISECOND) + ".png";

			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			// Log.info("Location of screenshot file "+screenShotsFolder+filename);

			FileUtils.copyFile(scrFile, new File(screenShotsFolder + filename));
			Reporter.setCurrentTestResult(testResult);
			Reporter.log("screenshot for " + testImgDir + filename + " url=" + driver.getCurrentUrl()
					+ "<div style=\"height:400px; width: 750px; overflow:scroll\"><a target='_blank' href='" + testImgDir + filename + "'> <img src=\"" + testImgDir + filename
					+ "\"></a></div>", true);
			// Reporter.log("<br><a target='_blank' href='"+testImgDir+filename+"'> <img src="+testImgDir+filename+" height='400' width='600'></a> <br>");
			Reporter.setCurrentTestResult(null);

		} catch (Exception e) {
			LOGGER.error("Failed to create screenshot " + e);
		}

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public String getCycleID() {
		return cycleID;
	}

	public void setCycleID(String cycleID) {
		this.cycleID = cycleID;
	}

	public void onStart(ISuite suite) {

		
	}

	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub

	}

}

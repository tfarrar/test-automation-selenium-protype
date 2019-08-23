package com.company.sampleapp.utils;

import java.io.File;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;




@Listeners({ TestMethodListenerExt.class })
public class TestBase {
	private static Logger LOGGER = Logger.getLogger(TestBase.class);
	public WebDriver driver;
	private WebDriver baseDriver;
	// protected List<List<String>> listOfLists = Lists.newArrayList();
	
	private static String browser = "firefox";
	private boolean useGrid = false;
	public Properties defaultProp;

	public void setProperties() {
		GlobalProps defaultPropertes = new GlobalProps();

		LOGGER.info("localdir=" + System.getProperty("user.dir"));
		defaultProp = defaultPropertes.loadParameters();
		LOGGER.info("useGrid=" + defaultProp.getProperty("useGrid"));

		useGrid = defaultProp.getProperty("useGrid").equalsIgnoreCase("true");

	}

	public WebDriver getDriver() {
		return driver;
	}

	@BeforeTest
	public void createDriver() throws Exception {

		setProperties();
		String browseName = defaultProp.getProperty("browserName");
		LOGGER.info("browseName=" + browseName);
		// this.driver = LocalDriverManager.getDriver();
		//
		if (useGrid) {
			// String browseName = defaultProp.getProperty("browseName");
			String platform = defaultProp.getProperty("os");
			String url = defaultProp.getProperty("urlGrid");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setBrowserName(browseName);
			capabilities.setCapability("platform", platform);
			capabilities.setCapability("seleniumProtocol", "WebDriver");
			// this.driver = LocalDriverManager.getDriver();
			baseDriver = new RemoteWebDriver(new URL(url), capabilities);

			// driver.register(new UniversalChecks());
		} else {
			this.createInstance(browseName);
		}

		EventFiringWebDriver efwd = new EventFiringWebDriver(baseDriver);
		WebDriverListener eventListener = new WebDriverListener(baseDriver);
		efwd.register(eventListener);

		driver = efwd;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@AfterTest
	public void tearDown() {
		if (driver != null) {
			try {
				driver.close();
				driver.quit();

			} catch (WebDriverException e) {
				System.out.println("***** CAUGHT EXCEPTION IN DRIVER TEARDOWN *****");
				System.out.println(e);
			}

		}

	}

	public void createInstance(String browserName) throws Exception {
		browser = browserName;

		if (browserName.toLowerCase().contains("firefox")) {
			baseDriver = new FirefoxDriver();
			// return driver;
		} else if (browserName.toLowerCase().contains("internet explorer")) {

			String filePathLocal = "src/test/resources/IEDriverServer32.exe";
			String filePathJenkins = "target/IEDriverServer32.exe";

			File f = new File(filePathLocal);
			File f2 = new File(filePathJenkins);
			if (f.exists() && !f.isDirectory()) {
				LOGGER.info("found filePath=" + filePathLocal);
				System.setProperty("webdriver.ie.driver", filePathLocal);
			} else if (f2.exists() && !f2.isDirectory()) {
				LOGGER.info("found filePath=" + filePathJenkins);
				System.setProperty("webdriver.ie.driver", filePathJenkins);
			} else {
				LOGGER.info("No filePath found");
			}

			baseDriver = new InternetExplorerDriver();
			// return driver;
		} else if (browserName.toLowerCase().contains("chrome")) {

			// CopyFileFromJar myExe = new CopyFileFromJar();
			// myExe.ExportResource("/drivers/chromedriver.exe");
			String filePathLocal = "src/test/resources/chromedriver.exe";
			String filePathJenkins = "target/test-classes/chromedriver2.12.exe";

			File f = new File(filePathLocal);
			File f2 = new File(filePathJenkins);
			if (f.exists() && !f.isDirectory()) {
				LOGGER.info("found filePath=" + filePathLocal);
				System.setProperty("webdriver.chrome.driver", filePathLocal);
			} else if (f2.exists() && !f2.isDirectory()) {
				LOGGER.info("found filePath=" + filePathJenkins);
				System.setProperty("webdriver.chrome.driver", filePathJenkins);
			} else {
				LOGGER.info("No filePath found");
			}

			baseDriver = new ChromeDriver();
			// return driver;
		} else {
			baseDriver = new FirefoxDriver();
			// return driver;
		}

	}

	public void waitSeconds(int secons) {
		LOGGER.info("Pausing for " + secons + " seconds: ");
		try {
			Thread.currentThread();
			int x = 1;
			while (x <= secons) {
				Thread.sleep(1000);
				// logger.info(" " + x );
				x = x + 1;
			}
			LOGGER.info("wait complete\n");
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	public static String getBroswer() {

		return browser;
	}

}

package com.orangehrmlive.demo.opensource.base;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.orangehrmlive.demo.opensource.Utilities.Utils;
import com.orangehrmlive.demo.opensource.pages.loginPage;

public class testbaseClass extends Utils{

	public WebDriver driver;

	static long time=System.currentTimeMillis();

	public static String reportfilepath=System.getProperty("user.dir")+"\\ExtentReports\\test_Report.html";
	static String logincredfilepath=System.getProperty("user.dir")+"\\PropertyFiles\\UserDetails.properties";
	static String chromeDriverpath=System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe";
	static String edgeDriverpath=System.getProperty("user.dir")+"\\Drivers\\msedgedriver.exe";

	public ExtentSparkReporter spark;
	public ExtentReports report;
	public ExtentTest test;

	Properties props;
	FileInputStream fis;

	loginPage lPage;

	static {
		System.setProperty("webdriver.chrome.driver", chromeDriverpath);
		System.setProperty("webdriver.edge.driver", edgeDriverpath);
	}

	@BeforeClass
	public void setUp() {
		spark=new ExtentSparkReporter(reportfilepath);
		report = new ExtentReports();
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setReportName("Extent Report");
		test = report.createTest("==========Test Started===================");
	}

	@BeforeMethod
	public void login() {	
		
		try {
			fis = new FileInputStream(logincredfilepath);
			props = new Properties();
			props.load(fis);	

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String browser=props.getProperty("browser");
		String URL=props.getProperty("URL");
		String userName=props.getProperty("userName");
		String passWord=props.getProperty("passWord");
		
		if(browser.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
			test.info("Chrome browser is Launched !");
		}
		else {
			driver=new EdgeDriver();
			test.info("Edge browser is Launched !");
		}
		
		lPage=new loginPage(driver);
		driver.manage().deleteAllCookies();
		driver.get(URL);
		driver.manage().window().maximize();
		Utils.WaitforelementClickable(lPage.getUsername(), driver);
		Utils.scrollToelement(lPage.getUsername(), driver);
		lPage.getUsername().sendKeys(userName);
		lPage.getPassword().sendKeys(passWord);
		lPage.getSubmit().click();
		Utils.WaitforelementClickable(lPage.getDashboard(), driver);

		if(Utils.iselementDisplayed(lPage.getDashboard())) {
			test.pass("Dashboard is Displayed !"+MediaEntityBuilder.createScreenCaptureFromBase64String(Utils.takeScreenshot(driver)));
		}
		else {
			test.fail("Dashboard is not Displayed !"+MediaEntityBuilder.createScreenCaptureFromBase64String(Utils.takeScreenshot(driver)));
			Assert.fail();
		}

	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	@AfterClass
	public void tearDown() {
		test.info("=====================Test Ended===========================");
		report.flush();		
	}

}

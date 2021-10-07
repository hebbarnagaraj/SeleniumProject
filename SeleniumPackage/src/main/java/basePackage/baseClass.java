package basePackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class baseClass {
	
	public static WebDriver driver;

	static {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\Drivers\\geckodriver.exe");
	}
	String browser=System.getProperty("browser");
	
	@SuppressWarnings("deprecation")
	@BeforeMethod
	public void setUp() {
		
		if(browser.equalsIgnoreCase("chrome")) {
			
			DesiredCapabilities capschrome = DesiredCapabilities.chrome();
			driver=new ChromeDriver(capschrome);
			driver.manage().window().maximize();
			
		}
		else {
			DesiredCapabilities capsfirefox = DesiredCapabilities.firefox();
			driver=new FirefoxDriver(capsfirefox);
			driver.manage().window().maximize();
		}
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	public static void waitforelementclickable(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
}

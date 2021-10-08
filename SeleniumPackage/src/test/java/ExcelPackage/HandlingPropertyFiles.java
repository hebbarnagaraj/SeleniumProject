package ExcelPackage;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import basePackage.baseClass;

public class HandlingPropertyFiles extends baseClass{
	
	

	@Test
	public void propertyFiles() throws Exception {
		
		String filepath=System.getProperty("user.dir")+"\\PropertyFiles\\UserDetails.properties";
		FileInputStream inStream = new FileInputStream(filepath);
		
		
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		
		Properties props = new Properties();
		props.load(inStream);
		String userName = props.getProperty("userName");
		String passWord=props.getProperty("passWord");
		
		System.out.println("UserName is :"+userName);
		System.out.println("PassWord is :"+passWord);
		
		
		WebElement userNameele=driver.findElement(By.xpath("//input[@id=\"txtUsername\"]"));
		waitforelementclickable(userNameele);

		userNameele.sendKeys(userName);
	
		System.out.println("============Entered UserName============");
		driver.findElement(By.xpath("//input[@id=\"txtPassword\"]")).sendKeys(passWord);
		System.out.println("============Entered passWord============");
		
		driver.findElement(By.xpath("//input[@name=\"Submit\"]")).click();
		System.out.println("============Clicked On Login Button============");
		WebElement dashBoard=driver.findElement(By.xpath("//a[@id=\"menu_dashboard_index\"]"));
		
		baseClass.waitforelementclickable(dashBoard);
	
		if(dashBoard.isDisplayed()) {
			System.out.println("===============Verified Dashboard===============");
		}
		else {
			System.out.println("==============Dashboard is not Visible============");
			Assert.fail();
		}
	}
}

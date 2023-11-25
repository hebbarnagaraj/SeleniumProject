package com.orangehrmlive.demo.opensource.Utilities;

import java.io.FileInputStream;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils{
	
	public static void WaitforelementClickable(WebElement ele , WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.pollingEvery(Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('style','background:yellow;border:2px solid red)');", ele);
	}
	public static void WaitelementVisible(WebElement ele, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.pollingEvery(Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('style','background:yellow;border:2px solid red)');", ele);
	}
	
	public static void sleep(long secs) {
		try {
			Thread.sleep(secs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean iselementDisplayed(WebElement ele) {
		try {
			if(ele.isDisplayed()) {
				return true;
			}
			else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public static String takeScreenshot(WebDriver driver) {
		String screenshotBase64 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
		return screenshotBase64;
	}
	
	@SuppressWarnings("resource")
	public static String readDatafromexcel(String filepath,String sheetName, int row , int col) {
		String value = null;
		try {
			FileInputStream fis = new FileInputStream(filepath);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			value = wb.getSheet(sheetName).getRow(row).getCell(col).getStringCellValue();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static void scrollToelement( WebElement ele,WebDriver driver) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
		js.executeScript("arguments[0].setAttribute('style','background:yellow;border:2px solid red)');", ele);
	}
	

}

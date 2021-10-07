package ActionPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import basePackage.baseClass;

public class actionClass extends baseClass{

	Actions act;
	
	@Test
	public void dubleclickAction() {
		act = new Actions(driver);
		
		driver.get("http://demo.guru99.com/test/simple_context_menu.html");
		WebElement clickbutton = driver.findElement(By.xpath("//button[.='Double-Click Me To See Alert']"));
		
		baseClass.waitforelementclickable(clickbutton);
		
		act.doubleClick(clickbutton).perform();
		System.out.println("=========Double CLick Worked==========");
		
		driver.switchTo().alert().accept();
		System.out.println("=========Alert Accepted==========");
	}
	
	
	@Test
	public void contextclickAction() {
		act = new Actions(driver);
		driver.get("http://demo.guru99.com/test/simple_context_menu.html");
		
		WebElement clickbutn = driver.findElement(By.xpath("//span[.='right click me']"));
		baseClass.waitforelementclickable(clickbutn);
		act.contextClick().perform();
		System.out.println("=========Right CLick Worked==========");
	}

	@Test
	public void dragAnddropAction() {
		act = new Actions(driver);
		
		driver.get("http://demo.guru99.com/test/drag_drop.html");
		
		WebElement fromElement = driver.findElement(By.xpath("//li[@id='credit2']//a"));
		WebElement toElement = driver.findElement(By.xpath("//*[@id='bank']//li"));
		baseClass.waitforelementclickable(fromElement);
		
		act.dragAndDrop(fromElement, toElement).perform();
		System.out.println("=========Drag and Drop Worked==========");
	}
	
	@Test
	public void movetoElementAction() throws Exception {
		act = new Actions(driver);
		
		String expectedText = "What's new in 3.2";
		
		driver.get("http://demo.guru99.com/test/tooltip.html");
		WebElement downloadbtn = driver.findElement(By.xpath("//*[@class='box']//*[.='Download now']"));
		act.clickAndHold().moveToElement(downloadbtn).perform();
		Thread.sleep(4000);
		WebElement tooltipElement = driver.findElement(By.xpath(".//*[@class='box']/div/a"));
		Thread.sleep(4000);
		String tooltext = tooltipElement.getText();
		Thread.sleep(4000);
		System.out.println("============Move to element Worked===============");
		System.out.println("Actual Tool tip Text is :"+tooltext);
		Assert.assertEquals(tooltext, expectedText);
	}
	
}

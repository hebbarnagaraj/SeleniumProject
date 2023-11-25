package com.orangehrmlive.demo.opensource.tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.orangehrmlive.demo.opensource.Utilities.Utils;
import com.orangehrmlive.demo.opensource.base.testbaseClass;
import com.orangehrmlive.demo.opensource.pages.UserManagementPage;
import com.orangehrmlive.demo.opensource.pages.commanPage;

public class AddEmployee extends testbaseClass{

	
	String testdatafilePath=System.getProperty("user.dir")+"\\testData\\userManagement.xlsx";
	commanPage cPage;
	UserManagementPage umPage;
	
	
	@Test
	public void testaddEmployee() {
		
		cPage=new commanPage(driver);
		umPage=new UserManagementPage(driver);
		
		String firstName=Utils.readDatafromexcel(testdatafilePath, "Sheet1", 1, 0);
		String middleName=Utils.readDatafromexcel(testdatafilePath, "Sheet1", 1, 1);
		String lastName=Utils.readDatafromexcel(testdatafilePath, "Sheet1", 1, 2);
		
		
		try {
			
			Utils.WaitforelementClickable(cPage.getPIMButton(), driver);
			cPage.getPIMButton().click();
			test.pass("Clicked on PIM Button !"+MediaEntityBuilder.createScreenCaptureFromBase64String(Utils.takeScreenshot(driver)));
			Utils.WaitforelementClickable(umPage.getAddEmployee(), driver);
			umPage.getAddEmployee().click();
			umPage.getFirstName().sendKeys(firstName);
			umPage.getMiddleName().sendKeys(middleName);
			umPage.getLastName().sendKeys(lastName);
			test.pass("Name details are entered !"+MediaEntityBuilder.createScreenCaptureFromBase64String(Utils.takeScreenshot(driver)));
			String employeeID = umPage.getEmployeeID().getText();
			System.out.println("Employee ID is :"+employeeID);
			cPage.getSaveButton().click();
			Utils.sleep(5000);
			test.pass("Employee ID is :\t"+employeeID+MediaEntityBuilder.createScreenCaptureFromBase64String(Utils.takeScreenshot(driver)));
			
		} catch (Exception e) {
			test.fail("Unable to create user !"+MediaEntityBuilder.createScreenCaptureFromBase64String(Utils.takeScreenshot(driver)));
		}
	}
}

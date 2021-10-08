package ExcelPackage;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import basePackage.baseClass;

public class handlingExcelFiles extends baseClass{
	
	@Test (dataProvider="getdataExcel")
	public void testDataexcel(String userName, String passWord) {
		
		driver.get("https://opensource-demo.orangehrmlive.com/");
	
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
	
	@DataProvider
	private static Object[][] getdataExcel(){
		String filepath = System.getProperty("user.dir")+"\\ExcelFiles\\ExcelData.xlsx";
		File file = new File(filepath);
		FileInputStream fis;
		XSSFWorkbook wb = null;
		
		try {
			fis = new FileInputStream(file);
			 wb=new XSSFWorkbook(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		XSSFSheet sheet;
		sheet = wb.getSheetAt(0);
		int rowCount = sheet.getLastRowNum();
		int colCount = sheet.getRow(0).getLastCellNum();
		

		Object[][] datatable=new Object[rowCount+1][colCount];
		
		for (int i = 0; i <= rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				String celldata = sheet.getRow(i).getCell(j).getStringCellValue();
				
				if(celldata!=null) {
					datatable[i][j]=sheet.getRow(i).getCell(j).getStringCellValue();
				}
				
			}
		}
		return datatable;
	}
}

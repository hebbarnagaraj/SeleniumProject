package com.orangehrmlive.demo.opensource.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class commanPage {

	
	
	@FindBy(xpath="//button[.=' Search ']")
	private WebElement searchButton;
	
	@FindBy(xpath="//li[.='Admin']")
	private WebElement Adminbutton;
	
	@FindBy(xpath="//li[.='PIM']")
	private WebElement PIMButton;
	
	@FindBy(xpath="//*[.=' Save ']")
	private WebElement saveButton;
	
	public commanPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}


	public WebElement getPIMButton() {
		return PIMButton;
	}


	public void setPIMButton(WebElement pIMButton) {
		PIMButton = pIMButton;
	}


	public WebElement getSaveButton() {
		return saveButton;
	}


	public void setSaveButton(WebElement saveButton) {
		this.saveButton = saveButton;
	}


	public WebElement getAdminbutton() {
		return Adminbutton;
	}


	public void setAdminbutton(WebElement adminbutton) {
		Adminbutton = adminbutton;
	}


	public WebElement getSearchButton() {
		return searchButton;
	}


	public void setSearchButton(WebElement searchButton) {
		this.searchButton = searchButton;
	}
	
	
}

package com.orangehrmlive.demo.opensource.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserManagementPage {

	

	
	@FindBy(xpath="//li[.='Add Employee ']")
	private WebElement addEmployee;
	
	@FindBy(xpath="//input[@placeholder='First Name']")
	private WebElement firstName;
	
	@FindBy(xpath="//input[@placeholder='Middle Name']")
	private WebElement middleName;
	
	@FindBy(xpath="//input[@placeholder='Last Name']")
	private WebElement lastName;
	
	@FindBy(xpath="//label[.='Employee Id']//parent::div//parent::div//input")
	private WebElement employeeID;
	
	public UserManagementPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getAddEmployee() {
		return addEmployee;
	}

	public void setAddEmployee(WebElement addEmployee) {
		this.addEmployee = addEmployee;
	}

	public WebElement getFirstName() {
		return firstName;
	}

	public void setFirstName(WebElement firstName) {
		this.firstName = firstName;
	}

	public WebElement getMiddleName() {
		return middleName;
	}

	public void setMiddleName(WebElement middleName) {
		this.middleName = middleName;
	}

	public WebElement getLastName() {
		return lastName;
	}

	public void setLastName(WebElement lastName) {
		this.lastName = lastName;
	}

	public WebElement getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(WebElement employeeID) {
		this.employeeID = employeeID;
	}
	
	

}

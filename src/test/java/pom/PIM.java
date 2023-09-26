package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import genericScripts.FetchDataFromPropertyFile;

public class PIM {
	WebDriver driver;
	String empfirstName;
	String empLastName;
	
	@FindBy(xpath="//a[.='Add Employee']")
	private WebElement addEmployee;
	
	@FindBy(xpath="//a[.='Employee List']")
	private WebElement employeeList;
	
	@FindBy(name="firstName")
	private WebElement firstName;
	
	@FindBy(name="lastName")
	private WebElement lastName;
	
	@FindBy(css="button[type='submit']")
	private WebElement save;
	
	@FindBy(xpath="//label[.='Employee Name']/../following-sibling::div/div/div/input")
	private WebElement employeeName;
	
	@FindBy(css="div.orangehrm-container div.oxd-table.orangehrm-employee-list div.oxd-table-body div.oxd-table-card")
	private List<WebElement> matchingEmployeesList;
	
	@FindBy(css="div.orangehrm-container div.oxd-table.orangehrm-employee-list div.oxd-table-body div.oxd-table-card div div:nth-child(3) div")
	private List<WebElement> matchingEmployeeFirstNameList;
	
	@FindBy(css="div.orangehrm-container div.oxd-table.orangehrm-employee-list div.oxd-table-body div.oxd-table-card div div:nth-child(4) div")
	private List<WebElement> matchingEmployeeLastNameList;
	
	public PIM(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void addEmployee() throws Exception
	{
		addEmployee.click();
		Thread.sleep(2000);
		
		empfirstName = FetchDataFromPropertyFile.getProperty("./resources/employeeData.properties", "firstname");
		empLastName = FetchDataFromPropertyFile.getProperty("./resources/employeeData.properties", "lastname");
		
		firstName.sendKeys(empfirstName);
		Thread.sleep(2000);
		
		lastName.sendKeys(empLastName);
		Thread.sleep(2000);
		
		save.click();
		Thread.sleep(2000);
	}
	
	public void verifyAddedEmployee() throws Exception
	{
		employeeList.click();
		Thread.sleep(2000);
		
		employeeName.sendKeys(empfirstName);
		Thread.sleep(2000);
		
		save.click();
		Thread.sleep(2000);
		
		Assert.assertNotEquals(matchingEmployeesList.size(), 0, "No records found");
		
		boolean present = false;
		for (int i=0;i<matchingEmployeeFirstNameList.size();i++) {
			WebElement empname = matchingEmployeeFirstNameList.get(i);
			if(empname.getText().equalsIgnoreCase(empfirstName) && matchingEmployeeLastNameList.get(i).getText().equals(empLastName))
			{
				present = true;
				break;
			}
		}
		
		Assert.assertTrue(present);
	}
}

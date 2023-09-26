package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import genericScripts.FetchDataFromPropertyFile;

public class PIM2 {
	WebDriver driver;
	
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
	
	@FindBy(css="button.oxd-icon-button.oxd-table-cell-action-space i.oxd-icon.bi-trash")
	private List<WebElement> deleteEmployeeButton;
	
	@FindBy(css="button.oxd-button.oxd-button--medium.oxd-button--label-danger.orangehrm-button-margin")
	private WebElement yesDeleteButton;
	
	public PIM2(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void addEmployee(String empfirstName, String empLastName) throws Exception
	{
		addEmployee.click();
		Thread.sleep(2000);
		
//		WebDriverWait wait = new WebDriverWait(driver, 10);
//		wait.until(ExpectedConditions.urlToBe("https://opensource-demo.orangehrmlive.com/web/index.php/pim/addEmployee"));
		
		firstName.sendKeys(empfirstName);
		Thread.sleep(2000);
		
		lastName.sendKeys(empLastName);
		Thread.sleep(2000);
		
		save.click();
		Thread.sleep(2000);
		
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/pim/addEmployee");
	}
	
	public void verifyAddedEmployeeAndDelete(String empfirstName, String empLastName) throws Exception
	{
		employeeList.click();
		Thread.sleep(2000);
		
		employeeName.sendKeys(empfirstName);
		Thread.sleep(2000);
		
		save.click();
		Thread.sleep(2000);
		
		if(matchingEmployeesList.size()==0)
		{
			System.out.println("No records found");
		}
			
		else
		{
			int index=0;
			boolean present = false;
			for (int i=0;i<matchingEmployeeFirstNameList.size();i++) {
				WebElement empname = matchingEmployeeFirstNameList.get(i);
				if(empname.getText().equalsIgnoreCase(empfirstName) && matchingEmployeeLastNameList.get(i).getText().equals(empLastName))
				{
					index=i;
					present = true;
					break;
				}
			}
			
			if(present)
			{
				deleteEmployeeButton.get(index).click();
				Thread.sleep(2000);
				yesDeleteButton.click();
			}
				
			else
				System.out.println(empfirstName+" "+empLastName+" not added");
		}

		driver.navigate().refresh();
	}
}

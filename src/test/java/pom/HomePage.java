package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage {
	WebDriver driver;
	
	@FindBy(css="span.oxd-topbar-header-breadcrumb h6")
	private List<WebElement> dashboardBreadcrumb;
	
	@FindBy(css="p.oxd-userdropdown-name")
	private WebElement userdropdown;
	
	@FindBy(xpath="//a[.='Logout']")
	private WebElement logoutLink;
	
	@FindBy(xpath="//span[.='PIM']")
	private WebElement pim;
	
	public HomePage(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void verifyLogin() throws Exception
	{
		Assert.assertNotEquals(dashboardBreadcrumb.size(), 0,"Invalid login credentials");
//		return dashboardBreadcrumb.size()!=0;	
	}
	
	public void logout() throws Exception
	{
		userdropdown.click();
		Thread.sleep(2000);
		
		logoutLink.click();
		Thread.sleep(2000);
	}
	
	public void clickPIM() throws Exception
	{
		pim.click();
		Thread.sleep(2000);
		
	}
}



package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericScripts.FetchDataFromPropertyFile;

public class LoginPage {
	WebDriver driver;
	
	@FindBy(name="username")
	private WebElement username;
	
	@FindBy(name="password")
	private WebElement password;
	
	@FindBy(css="button[type='submit']")
	private WebElement login;

	public LoginPage(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterUsernamePasswordFromScript(String usernameData, String passwordData) throws Exception
	{
//		/*
		username.sendKeys(usernameData);
		Thread.sleep(2000);
		password.sendKeys(passwordData);
		Thread.sleep(2000);
//		*/
	}

	public void enterUsernamePassword() throws Exception
	{
		username.sendKeys(FetchDataFromPropertyFile.getProperty("./resources/login.properties", "username"));
		Thread.sleep(2000);
		password.sendKeys(FetchDataFromPropertyFile.getProperty("./resources/login.properties", "password"));
		Thread.sleep(2000);
	}
	
	public void clickLoginButton() throws Exception
	{
		login.click();
		Thread.sleep(2000);
	}
}

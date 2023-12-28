/**
 * 
 */
package tnp.qa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import tnp.qa.pageObjects.LandingPage;
import tnp.qa.pageObjects.LoginPage;
import tnp.qa.testbase.TestBase;

/**
 * @author Avinash
 *
 */
public class LoginTest extends TestBase {
	
	public WebDriver driver;
	Actions action;
	public String myAccount = "//a//*[text()='My Account']";
	public String loginBtn = "//a[text()='Login']";
	
	
	
	@BeforeMethod
	public void setUp() {
		driver = initializeBrowser(properties.getProperty("browser"));
		LandingPage landingPage = new LandingPage(driver);
		landingPage.clickOnMyAccountLOV();
		landingPage.clickOnLoginLOV();
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
	@Test(priority = 1, dataProvider = "getValidLoginData")
	public void verifyLoginWithValidCredentialsUsingDataProviders(String emailId, String password) {
//		driver.findElement(By.xpath(myAccount)).click();
//		driver.findElement(By.xpath(loginBtn)).click();
//		driver.findElement(By.id("input-email")).sendKeys(emailId);
//		driver.findElement(By.id("input-password")).sendKeys(password);
//		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterValidEmailAddressInTextbox(emailId);
		loginPage.enterValidPasswordInTextbox(password);
		loginPage.clickOnLoginButton();
		
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
	}
	
	@Test(priority = 2)
	public void verifyLoginWithValidCredentials() {
//		driver.findElement(By.xpath(myAccount)).click();
//		driver.findElement(By.xpath(loginBtn)).click();
//		driver.findElement(By.id("input-email")).sendKeys(properties.getProperty("validEmail"));
//		driver.findElement(By.id("input-password")).sendKeys(properties.getProperty("validPassword"));
//		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
//		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterValidEmailAddressInTextbox(properties.getProperty("validEmail"));
		loginPage.enterValidPasswordInTextbox(properties.getProperty("validPassword"));
		loginPage.clickOnLoginButton();
		
		
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account informations")).isDisplayed());
	}
	
	@Test(priority = 3)
	public void verifyLoginWithInvalidCredentials() {
//		driver.findElement(By.xpath(myAccount)).click();
//		driver.findElement(By.xpath(loginBtn)).click();
//		driver.findElement(By.id("input-email")).sendKeys(CommonUtilities.generateEmailWithTimeStamp());
//		driver.findElement(By.id("input-password")).sendKeys(properties.getProperty("validPassword"));
//		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterInvalidEmailAddressInTextbox(properties.getProperty("invalidEmail"));
		loginPage.enterInvalidPasswordInTextbox(properties.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		
		Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Warning: No match for E-Mail Address and/or Password.']")).isDisplayed());
	}
	
	
	@Test(priority = 4, dependsOnMethods = "verifyLoginWithValidCredentials")
	public void verifyLoginWithValidCredentialUsingKeyboard() {
		action = new Actions(driver);
//		action.moveToElement(driver.findElement(By.xpath(myAccount))).click().perform();
//		driver.findElement(By.xpath(loginBtn)).click();
//		driver.findElement(By.id("input-email")).sendKeys(properties.getProperty("validEmail"));
//		driver.findElement(By.id("input-password")).sendKeys(properties.getProperty("validPassword"));
//		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		LoginPage loginPage = new LoginPage(driver);
		//action.moveToElement(driver.findElement(By.id("input-email"))).sendKeys(properties.getProperty("validEmail")).perform();
		//action.moveToElement(driver.findElement(By.id("input-password"))).sendKeys(properties.getProperty("validPassword")).perform();
		action.moveToElement(loginPage.enterValidEmailAddressInTextboxUsingActions()).sendKeys(loginPage.enterValidEmailAddressInTextboxUsingActions(), properties.getProperty("validEmail")).perform();
		action.moveToElement(loginPage.enterValidPasswordInTextboxUsingActions()).sendKeys(Keys.TAB).sendKeys(properties.getProperty("validPassword")).perform();
		action.moveToElement(loginPage.clickOnLoginButtonUsingActions()).click().perform();
		
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
	}
	
	

}

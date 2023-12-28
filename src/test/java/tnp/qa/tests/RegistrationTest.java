/**
 * 
 */
package tnp.qa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import tnp.qa.testbase.TestBase;
import tnp.qa.utils.CommonUtilities;

/**
 * @author Avinash
 *
 */
public class RegistrationTest extends TestBase {
	
	public WebDriver driver;
	Actions action;
	public String myAccount = "//a//*[text()='My Account']";
	public String register = "//a[text()='Login']";
	
	
	
	@BeforeMethod
	public void setUp() {
		driver = initializeBrowser(properties.getProperty("browser"));
		
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
	@Test
	public void verifyAccountRegistrationWithMandatoryFields() {
		driver.findElement(By.xpath("//a//*[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("Test");
		driver.findElement(By.id("input-lastname")).sendKeys("User1+1");
		driver.findElement(By.id("input-email")).sendKeys(CommonUtilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("9876543210");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.xpath("//*[@name='agree']")).click();
		driver.findElement(By.xpath("//*[@class='btn btn-primary']")).click();
		
		String accountCreationMessage = driver.findElement(By.xpath("//div//*[text()='Your Account Has Been Created!']")).getText();
		
		Assert.assertEquals(accountCreationMessage, "Your Account Has Been Created!", "The account creation success message is not displayed.");
		
	}

}

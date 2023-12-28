/**
 * 
 */
package tnp.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Avinash
 *
 */
public class LandingPage {
	
	WebDriver driver;
	
	@FindBy(xpath = "//a//*[text()='My Account']")
	private WebElement myAccountLOV;
	
	@FindBy(xpath = "//a[text()='Login']")
	private WebElement loginLOV;
	
	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}
	
	//Below are the methods to perform actions on the webpage
	
	public void clickOnMyAccountLOV() {
		myAccountLOV.click();
	}
	
	public void clickOnLoginLOV() {
		loginLOV.click();
	}

}

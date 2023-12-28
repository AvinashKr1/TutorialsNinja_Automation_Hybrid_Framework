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
public class LoginPage {
	
	private WebDriver driver;
	
	@FindBy(xpath = "//a//*[text()='My Account']")
	private WebElement myAccountLOV;
	
	@FindBy(xpath = "//a[text()='Login']")
	private WebElement loginLOV;
	
	@FindBy(id = "input-email")
	private WebElement emailTextbox;
	
	@FindBy(id = "input-password")
	private WebElement password;
	
	@FindBy(xpath = "//*[@class='btn btn-primary' and @value='Login']")
	private WebElement loginBtn;
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}
	//Below are the methods to perform actions on the webpage
	
	public void enterValidEmailAddressInTextbox(String validEmail) {
		emailTextbox.sendKeys(validEmail);
	}
	
	public void enterValidPasswordInTextbox(String validPassword) {
		password.sendKeys(validPassword);
	}
	
	public void clickOnLoginButton() {
		loginBtn.click();
	}
	
	public void enterInvalidEmailAddressInTextbox(String invalidEmail) {
		emailTextbox.sendKeys(invalidEmail);
	}
	
	public void enterInvalidPasswordInTextbox(String invalidPassword) {
		password.sendKeys(invalidPassword);
	}
	
	public WebElement enterValidEmailAddressInTextboxUsingActions() {
		return emailTextbox;
	}
	
	public WebElement enterValidPasswordInTextboxUsingActions() {
		return password;
	}
	
	public WebElement clickOnLoginButtonUsingActions() {
		return loginBtn;
	}
}

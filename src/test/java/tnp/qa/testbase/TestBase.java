/**
 * 
 */
package tnp.qa.testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.DataProvider;

import tnp.qa.utils.ExcelUtilities;

/**
 * @author Avinash
 *
 */
public class TestBase {
	
	private WebDriver driver;
	protected Properties properties;
	private final String CONFIG_FILE_PATH = "E:\\Eclipse Workspace\\TutorialsNinjaProj\\src\\main\\java\\tnp\\qa\\config\\config.properties";
	private final int IMPLICIT_WAIT_TIME = 20;
	private final int PAGE_LOAD_TIME = 10;

	public TestBase() {
		File file = new File(CONFIG_FILE_PATH);
		try {
			FileInputStream fis = new FileInputStream(file);
			properties = new Properties();
			properties.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public WebDriver initializeBrowser(String browser) {
		if(browser.toUpperCase().equals("CHROME")) {
			driver = new ChromeDriver();
		}else {
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.get(properties.getProperty("URL"));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(PAGE_LOAD_TIME));
		
		return driver;
	}
	
	@DataProvider //(name = "getValidLogin")
	public Object[][] getValidLoginData(){
		ExcelUtilities xlUtil = new ExcelUtilities();
		return xlUtil.getExcelData(properties.getProperty("sheetName"));
	}

}

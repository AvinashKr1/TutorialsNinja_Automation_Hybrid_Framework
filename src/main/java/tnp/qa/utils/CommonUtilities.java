/**
 * 
 */
package tnp.qa.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

/**
 * @author Avinash
 *
 */
public class CommonUtilities {
	public static void main(String [] args) {
		System.out.println(generateTimeStamp());
		System.getProperties().list(System.out);
		
	}
	
	public static String generateEmailWithTimeStamp() {
		Date date = new Date();
		String timeStamp = date.toString().replace(" ", "_").replace(":", "_");
		return "test-user1"+timeStamp+"@gmail.com";
	}
	
	public static String generateTimeStamp() {
		String pattern = "dd-MMM-yyyy_HH-mm-ss";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String timeStamp = sdf.format(cal.getTime());
				
		return timeStamp;
	}
	
	public static String captureScreenshot(WebDriver driver, String testName) {
		
		String destFilePath = System.getProperty("user.dir")+"\\screenshots\\"+testName+"-"+CommonUtilities.generateTimeStamp()+".png";
		
		File srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(srcScreenshot, new File(destFilePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return destFilePath;
	}

}

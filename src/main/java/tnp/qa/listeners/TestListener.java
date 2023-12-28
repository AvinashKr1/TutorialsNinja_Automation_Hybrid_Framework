/**
 * 
 */
package tnp.qa.listeners;

import java.awt.Desktop;
import java.io.File;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import tnp.qa.utils.CommonUtilities;
import tnp.qa.utils.ExtentReporter;

/**
 * @author Avinash
 *
 */
public class TestListener implements ITestListener{

	String testName;
	ExtentReports extentReport;
	ExtentTest extentTest;
	
	@Override
	public void onStart(ITestContext context) {
		System.out.println("Execution of project statrted...");
		extentReport = ExtentReporter.getInstanceOf().generateExtentReport();
	}
	@Override
	public void onTestStart(ITestResult result) {
		 System.out.println(result.getInstanceName());
		 testName = result.getName();
		 System.out.println(testName+" execution started...");
		 
		 extentTest = extentReport.createTest(testName);
		 extentTest.log(Status.INFO, testName+" execution started.");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		testName = result.getName();
		System.out.println(testName+" executed successfully.");
		extentTest.log(Status.PASS, testName+" executed successfully");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		testName = result.getName();
		System.out.println("Screenshot taken.");
		extentTest.log(Status.INFO, "Screenshot taken.");
		
		//getting the driver object
		WebDriver driver = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String destFilePath = CommonUtilities.captureScreenshot(driver, testName);
		
		System.out.println(testName+" failed.");
		extentTest.log(Status.FAIL, testName+" failed.");
		
		extentTest.addScreenCaptureFromPath(destFilePath);
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		testName = result.getName();
		System.out.println(testName+" execution skipped");

		extentTest.log(Status.SKIP, testName+" execution skipped.");
	}

	

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Project execution is finished.");

		extentTest.log(Status.INFO, "Project execution is finished.");
		extentReport.flush();
		
		String reportPath = ExtentReporter.getInstanceOf().getExtentReportFilePath();
		try {
			File reportFile = new File(reportPath);
			Desktop.getDesktop().browse(reportFile.toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		/*//below code sends the extent report to the user's email
		EmailAttachment attachment = new EmailAttachment();

        attachment.setPath(reportPath);


        attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setDescription(" Test Execution Report");
            attachment.setName("Automation Test Execution Report");
   
            // Create the email message
            MultiPartEmail email = new MultiPartEmail();
            email.setHostName("smtp.gmail.com");
            email.setSSLOnConnect(true);
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("user1@gmail.com", "password"));
            try {
				email.addTo("user2@gmail.com", "Test");
				email.setFrom("user1@gmail.com", "Me");
	            email.setSubject("Automation Test Execution Report");
	            email.setMsg("Automation Test Execution Report");
	            // add the attachment
	            email.attach(attachment);
	   
	            // send the email
	            email.send();
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            */
   
           
		
	}
	
	

}

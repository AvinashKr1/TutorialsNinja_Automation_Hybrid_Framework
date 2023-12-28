package tnp.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
	private Properties properties;
	private final String CONFIG_FILE_PATH = "E:\\Eclipse Workspace\\TutorialsNinjaProj\\src\\main\\java\\tnp\\qa\\config\\config.properties";
	private static String path;
	//EXTENT_REPORT_FILE_PATH;
	
	
	
	private ExtentReporter() {
		File file = new File(CONFIG_FILE_PATH);
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			properties = new Properties();
			properties.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//path= System.getProperty("user.dir")+"\\reports\\"+properties.getProperty("testGroup")+"_"+CommonUtilities.generateTimeStamp()+".html";
		
	}
	
	public static ExtentReporter getInstanceOf() {
		return new ExtentReporter();
	}
	
	public ExtentReports generateExtentReport() {
		//getInstanceOf();
		ExtentReports extentReport = new ExtentReports();
		path= System.getProperty("user.dir")+"\\reports\\"+properties.getProperty("testGroup")+"_"+CommonUtilities.generateTimeStamp()+".html";
		File reportFile = new File(path); 
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFile);
		
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setDocumentTitle("TNP Automation Report");
		sparkReporter.config().setReportName("TutorialsNinja Test Automation Report");
		sparkReporter.config().setTimeStampFormat("dd-MMM-YYYY hh:mm:ss");
		
		extentReport.attachReporter(sparkReporter);
		
		extentReport.setSystemInfo("Application URL", properties.getProperty("URL"));
		extentReport.setSystemInfo("Browser Name", properties.getProperty("browser"));
		extentReport.setSystemInfo("User Email", properties.getProperty("validEmail"));
		extentReport.setSystemInfo("User Name", System.getProperty("user.name"));
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		return extentReport;
	}
	
	public String getExtentReportFilePath() {
		return ExtentReporter.path;
	}

}

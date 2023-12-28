package experiment;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import tnp.qa.utils.ExtentReporter;

public class Example {
	public static void main(String [] args) {
		System.out.println(Experiments.getInstanceOf().getVar());

		System.out.println("Path 1: "+ExtentReporter.getInstanceOf().getExtentReportFilePath());
		System.out.println("Execution of project statrted...");
		ExtentReporter extentReporter = ExtentReporter.getInstanceOf();

		ExtentReports reports = extentReporter.generateExtentReport();
		System.out.println("Path 2: "+ExtentReporter.getInstanceOf().getExtentReportFilePath());
		int x=10000;
		while(x>0) {
			ExtentTest test = reports.createTest("ABCD Test");
			test.log(Status.PASS, "Passed success.");
			x--;
		}
		
		System.out.println("Path 3: "+ExtentReporter.getInstanceOf().getExtentReportFilePath());

		
	}

}

package com.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtendReport {
	// creating obj
	static ExtentTest logger = null;
	static ExtentReports report = null;
	static ExtentHtmlReporter htmlReporter;

	public static void extendReport() {
		htmlReporter = new ExtentHtmlReporter("./TestReports/ExtentReport/TestReport.html");
		System.out.println("Report created");
		report = new ExtentReports();
		report.attachReporter(htmlReporter);
		logger = report.createTest("MyFirstTest", "Sample description");
	}
	
	public static void completeReport(){
		report.flush();
	}

	/**
	 * To have information on extent report.
	 * 
	 * @param info
	 * @author vinothkumar.p08@infosys.com
	 */
	public static void logInfo(String info) {
		logger.log(Status.INFO, info);
	}

	/**
	 * This method is to show the pass status
	 * 
	 * @param details
	 */
	public static void passStatus(String details) {
		logger.log(Status.PASS, details);
	}

	/**
	 * This method is to show the fail status
	 * 
	 * @param details
	 */
	public static void faileStatus(String details) {
		logger.log(Status.FAIL, details);
	}

}

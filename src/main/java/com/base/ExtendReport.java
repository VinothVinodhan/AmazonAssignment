package com.base;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtendReport {
	// creating obj
	static ExtentReports report;
	static ExtentTest logger;

	public static void extendReport() {
		report = new ExtentReports(System.getProperty("user.dir") + File.separator + "//TestReport//TestReport.html");
		System.out.println("report is: " + report);
		logger = report.startTest("Automation Report");
	}

	public static void logInfo(String info) {
		logger.log(LogStatus.INFO, info);
	}
}

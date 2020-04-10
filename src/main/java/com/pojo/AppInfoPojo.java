package com.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "name", "APP_PACKAGE", "APP_ACTIVITY", "path", "AppiumIP", "Port", "TestData", "TestSheetName" })

public class AppInfoPojo {
	@JsonProperty("name")
	private String name;
	@JsonProperty("APP_PACKAGE")
	private String aPPPACKAGE;
	@JsonProperty("APP_ACTIVITY")
	private String aPPACTIVITY;
	@JsonProperty("path")
	private String path;
	@JsonProperty("AppiumIP")
	private String appiumIP;
	@JsonProperty("Port")
	private int port;
	@JsonProperty("TestData")
	private String testData;
	@JsonProperty("TestSheetName")
	private String testSheetName;

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("APP_PACKAGE")
	public String getAPPPACKAGE() {
		return aPPPACKAGE;
	}

	@JsonProperty("APP_PACKAGE")
	public void setAPPPACKAGE(String aPPPACKAGE) {
		this.aPPPACKAGE = aPPPACKAGE;
	}

	@JsonProperty("APP_ACTIVITY")
	public String getAPPACTIVITY() {
		return aPPACTIVITY;
	}

	@JsonProperty("APP_ACTIVITY")
	public void setAPPACTIVITY(String aPPACTIVITY) {
		this.aPPACTIVITY = aPPACTIVITY;
	}

	@JsonProperty("path")
	public String getPath() {
		return path;
	}

	@JsonProperty("path")
	public void setPath(String path) {
		this.path = path;
	}

	@JsonProperty("AppiumIP")
	public String getAppiumIP() {
		return appiumIP;
	}

	@JsonProperty("AppiumIP")
	public void setAppiumIP(String appiumIP) {
		this.appiumIP = appiumIP;
	}

	@JsonProperty("Port")
	public int getPort() {
		return port;
	}

	@JsonProperty("Port")
	public void setPort(int port) {
		this.port = port;
	}

	@JsonProperty("TestData")
	public String getTestData() {
		return testData;
	}

	@JsonProperty("TestData")
	public void setTestData(String testData) {
		this.testData = testData;
	}

	@JsonProperty("TestSheetName")
	public String getTestSheetName() {
		return testSheetName;
	}

	@JsonProperty("TestSheetName")
	public void setTestSheetName(String testSheetName) {
		this.testSheetName = testSheetName;
	}
}

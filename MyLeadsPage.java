package com.sdet.screens;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.sdet.testng.api.base.ProjectSpecificMethods;



public class MyLeadsPage extends ProjectSpecificMethods {

	public MyLeadsPage(RemoteWebDriver driver, ExtentTest node){
		this.driver = driver;
		node = node;
		
	}
	
	
	// Click Create Leads 
	public CreateLeadPage clickCreateLead(){
		click(locateElement("link","Create Lead"));
		return new CreateLeadPage(driver, node);
	}


}

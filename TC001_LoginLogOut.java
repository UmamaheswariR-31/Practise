package com.sdet.testcase;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sdet.screens.LoginPage;
import com.sdet.testng.api.base.ProjectSpecificMethods;

public class TC001_LoginLogOut extends ProjectSpecificMethods{	

	@BeforeTest
	public void setValues() {
		testCaseName = "Login and LoginOut";
		testDescription = "Login testCase using DemoSalesManager UserName and LogOut";
		nodes = "Leads";
		authors = "Hari";
		category = "Smoke";
		dataSheetName = "TC001";
	}

	@Test(dataProvider = "fetchData")
	public void createLeaf(String uName, String pwd) {
		new LoginPage(driver, node)
		.enterUserName(uName)
		.enterPassword(pwd)
		.clickLogin()
		.clickLogout();		
	}


}






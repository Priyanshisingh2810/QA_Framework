package com.Regression.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.locators.allPages.XpathInterface;
import com.qa.baseclass.Baseclass;
import com.qa.paths.WebdriverPaths;
import com.qa.selenium.core.driver.IScreenAction;
import com.qa.selenium.core.element.IElementActions;
import com.qa.selenium.core.element.IWaitStrategy;
import com.qa.utils.Utils;
import com.testdata.allPages.TestDataInterface;

public class LoginTest extends Baseclass {

	@Parameters("Browser")
	@Test(priority = 1)
	public void userLogin(String Browser) throws IOException {

		try {
			extenttest = extent.createTest(
					"Verify that User Is Able To Login With Valid Credentials")
					.assignCategory(regressionSuite_tag + "_" + env);
			node = extenttest.createNode("Report");

			Browser = configFile.getProperty("Browser");

			if (Browser.equalsIgnoreCase("chrome")) {

				// Call Chrome function WebdriverPaths class
				WebdriverPaths.Webdriver_chrome();

			}

			else if (Browser.equalsIgnoreCase("firefox")) {

				// Call Firefox function for WebdriverPaths class
				WebdriverPaths.Webdriver_firefox();
			}

			// Open free crm login page as per the environment selected by user
			node.log(Status.INFO, "STEP1 : Opening the FreeCRM--->"
					+ configFile.getProperty("Environment") + "---> url");

			driver.get(urlData.getProperty("SiteUrl"));

			// Wait for the visibility of login form
			node.log(Status.INFO,
					"STEP2 : Waiting for the visibility of login form");

			IWaitStrategy.waitForVisiblity(node, driver,
					XpathInterface.Site.loginPageView);

			node.log(Status.INFO, "STEP3 : Login form is visible now");

			// Call the login function from utils class
			node.log(Status.INFO,
					"STEP4 : Call the login function from utils class");

			Utils.userLogin(node, driver,
					Baseclass.credentials.getProperty("EmailId"),
					Baseclass.credentials.getProperty("Password"));

			// Wait for the visibility of dashboad page after login
			node.log(Status.INFO,
					"STEP5 : Waiting for the visibility of dashboad page after login");

			IWaitStrategy.waitForVisiblity(node, driver,
					XpathInterface.DashBoard.tabView);

			node.log(Status.INFO,
					"STEP6 : Dashboard page is visible after login");

			// Verify that logged in user name is visible on top of Dashboard
			// page
			node.log(Status.INFO,
					"STEP7 : Verify that logged in user name is visible on top of Dashboard page");

			IElementActions
					.assertTrue(
							extenttest,
							driver,
							TestDataInterface.Dashboard.userName_passCase,
							"STEP8 :BUG:Logged in user name not coming on dashboard page",
							"STEP8 :EXPECTED:Logged in user name is coming on dashboard page");

			// Take Screenshot of dashboard after user login
			IScreenAction.captureScreenShot_PassCase("UserLogin", "Pass",
					driver);

		} catch (AssertionError userLogin) {

			node.log(
					Status.INFO,
					"EXCEPTION BLOCK 1 : FAIL : Some Assertion Error Caught In userLogin test in LoginTest class");

			// Take the failed case screenshot
			String screenpath = IScreenAction.captureScreenShot_FailedCase(
					driver, "UserLogin_Fail");
			node.log(
					Status.FAIL,
					"Some Assertion Error"
							+ extenttest.addScreenCaptureFromPath(screenpath));

			userLogin.printStackTrace();
			node.fail(userLogin);
			Assert.fail();
			extent.flush();
		}

		catch (Exception userLogin) {
			node.log(
					Status.INFO,
					"EXCEPTION BLOCK 2 : FAIL : Some Exception Error Caught In userLogin test in LoginTest class");

			// Take the failed case screenshot
			String screenpath = IScreenAction.captureScreenShot_FailedCase(
					driver, "UserLogin_Fail");
			node.log(
					Status.FAIL,
					"Some Exception Error"
							+ extenttest.addScreenCaptureFromPath(screenpath));

			userLogin.printStackTrace();
			node.fail(userLogin);
			Assert.fail();
			extent.flush();

			// TODO: handle exception
		}

	}

}

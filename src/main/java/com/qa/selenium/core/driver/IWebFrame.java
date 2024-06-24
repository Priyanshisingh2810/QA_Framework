package com.qa.selenium.core.driver;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.qa.baseclass.Baseclass;

public class IWebFrame extends Baseclass {

	/**
	 * The "switchToIframe_UsingFrameId" function is used to switch to iframe with
	 * the help of ID that can be passed on run time *
	 *
	 * @author Daffodil Software Private Limited
	 * @version 1.0
	 * @since 12.08.2021
	 */
	public static void switchToIframe_UsingFrameId(WebDriver driver, ExtentTest test, String frameId)
			throws InterruptedException {

		driver.switchTo().frame(frameId);

	}

	/**
	 * The "switchBackToMainFrame" function is used to switch back to Main frame
	 *
	 * @author Daffodil Software Private Limited
	 * @version 1.0
	 * @since 12.08.2021
	 */
	public static void switchBackToMainFrame(WebDriver driver, ExtentTest test) throws InterruptedException {

		driver.switchTo().defaultContent();

	}

}

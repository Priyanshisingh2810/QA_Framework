package com.qa.selenium.core.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qa.baseclass.Baseclass;

public class IElementActions extends Baseclass {

	/**
	 * The "clickelement" function is used to click on any of the element
	 * present on webPage
	 *
	 * @param test
	 *            Extent Test Instance
	 * @param driver
	 *            Selenium WebDriver Instance *
	 * @param Xpath
	 *            Pass xpath of element at run time
	 * @author Daffodil Software Private Limited
	 * @version 1.0
	 * @since 01.08.2021
	 */
	public static void clickelement(ExtentTest test, WebDriver driver,
			String Xpath) {
		driver.findElement(By.xpath(Xpath)).click();
	}

	/**
	 * The "assertTrue" function is used to assert is element present on
	 * webPage*
	 *
	 * @param test
	 *            Extent Test Instance
	 * @param driver
	 *            Selenium WebDriver Instance *
	 * @param xpath
	 *            Pass xpath of element at run time
	 * @param BugMessage
	 *            Input message that needs to be shown when assert fails
	 * @param PassMessage
	 *            Input message that needs to be shown when assert pass
	 * @author Daffodil Software Private Limited
	 * @version 1.0
	 * @since 01.08.2021
	 */
	public static void assertTrue(ExtentTest test, WebDriver driver,
			String xpath, String BugMessage, String PassMessage) {
		Assert.assertTrue(driver.findElements(By.xpath(xpath)).size() > 0,
				BugMessage);
		System.out.println(PassMessage);
		node.log(Status.INFO, PassMessage);
	}

	/**
	 * The "assertFalse" function is used to assert is element not present on
	 * webPage*
	 *
	 * @param test
	 *            Extent Test Instance
	 * @param driver
	 *            Selenium WebDriver Instance *
	 * @param xpath
	 *            Pass xpath of element at run time
	 * @param BugMessage
	 *            Input message that needs to be shown when assert fails
	 * @param PassMessage
	 *            Input message that needs to be shown when assert pass
	 * @author Daffodil Software Private Limited
	 * @version 1.0
	 * @since 01.08.2021
	 */
	public static void assertFalse(ExtentTest test, WebDriver driver,
			String xpath, String BugMessage, String PassMessage) {
		Assert.assertFalse(driver.findElements(By.xpath(xpath)).size() > 0,
				BugMessage);
		System.out.println(PassMessage);
		node.log(Status.INFO, PassMessage);
	}

	/**
	 * The "clickAndInput_usingInterface" function firstly clicks on any input
	 * field and then input the data as per need by fetching data from an
	 * interface *
	 *
	 * @param test
	 *            Extent Test Instance
	 * @param driver
	 *            Selenium WebDriver Instance *
	 * @param fieldXpath
	 *            Pass xpath of element at run time
	 * @param fieldValue
	 *            Fetch the value from interface
	 * @param fieldName
	 *            Give name of field
	 * @author Daffodil Software Private Limited
	 * @version 1.0
	 * @since 01.08.2021
	 */
	public static void clickAndInput_usingInterface(ExtentTest test,
			WebDriver driver, String fieldXpath, String fieldValue,
			String fieldName) throws InterruptedException {

		// Click on field
		IElementActions.clickelement(node, driver, fieldXpath);
		node.log(Status.INFO, "FUNCTION-SUB-STEP 1 : Clicked on " + fieldName
				+ " field");

		// Input value in field as per need by entering values at run time
		driver.findElement(By.xpath(fieldXpath)).sendKeys(fieldValue);
		node.log(Status.INFO, "FUNCTION-SUB-STEP 2 :  " + fieldName
				+ " entered");
	}

	/**
	 * The "IsElementPresent" function is used to wait till element present*
	 *
	 * @param driver
	 *            Selenium WebDriver Instance *
	 * @param xpath
	 *            xpath of element at run time
	 * @author Daffodil Software Private Limited
	 * @version 1.0
	 * @since 01.08.2021
	 */
	public static boolean IsElementPresent(ExtentTest test, WebDriver driver,
			String string_xpath) {

		boolean found = false;

		try {
			if (driver.findElements(By.xpath(string_xpath)).size() > 0) {
				found = true;
			}
		} catch (Throwable e) {
			found = false;
		}

		return found;
	}

}

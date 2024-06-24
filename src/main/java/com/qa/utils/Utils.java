package com.qa.utils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.locators.allPages.XpathInterface;
import com.qa.baseclass.Baseclass;
import com.qa.selenium.core.element.IElementActions;

public class Utils extends Baseclass {

	/**
	 * The "isLinkBroken" function is used to check how many broken links
	 * available based on response.
	 *
	 * @param URL
	 *            url will enter
	 * @author Daffodil Software Private Limited
	 * @version 1.0
	 * @since 01.08.2021
	 ***/
	public static String isLinkBroken(URL url) throws Exception

	{

		String response = "";

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		try

		{

			connection.connect();

			response = connection.getResponseMessage();

			connection.disconnect();

			return response;

		}

		catch (Exception exp)

		{

			return exp.getMessage();

		}

	}

	/**
	 * The "findAllLinks" function is used fetch data in list based on same tag
	 * or you wan modify this functiona as per your requirement .
	 *
	 * @param driver
	 *            Selenium WebDriver Instance *
	 * @author Daffodil Software Private Limited
	 * @version 1.0
	 * @since 01.08.2021
	 ***/
	public static List<WebElement> findAllLinks(WebDriver driver)

	{

		List<WebElement> elementList = new ArrayList<WebElement>();

		elementList = driver.findElements(By.tagName("a"));

		elementList.addAll(driver.findElements(By.tagName("img")));

		List<WebElement> finalList = new ArrayList<WebElement>();

		for (WebElement element : elementList)

		{

			if (element.getAttribute("href") != null
					&& element.getAttribute("href").contains("  ")) // add name

			{

				finalList.add(element);

			}

		}

		return finalList;

	}

	/**
	 * The "ChangeDateFormat" function is used to change date format .
	 *
	 * @param NewFormat
	 *            In which formate you want to chagne
	 * @param Date
	 *            date instance
	 * @author Daffodil Software Private Limited
	 * @version 1.0
	 * @since 01.08.2021
	 ***/
	public static String ChangeDateFormat(String NewFormat, String Date) {

		try {

			DateFormat formatter1 = new SimpleDateFormat(NewFormat);
			Date = formatter1.format(Date);

		} catch (Exception e) {

		}
		return Date;
	}

	/**
	 * The "getRandomNumberbetweenTwoLimits" function is used to generate random
	 * number between given limit*
	 *
	 * @param lowerBound
	 *            minimum range *
	 * @param upperBound
	 *            maximum range
	 * @author Daffodil Software Private Limited
	 * @version 1.0
	 * @since 01.08.2021
	 */
	public static int getRandomNumberbetweenTwoLimits(int lowerBound,
			int upperBound) {
		int randomNumber = 0;

		try {
			Random random = new Random();
			randomNumber = random.nextInt(upperBound - lowerBound) + lowerBound;
		} catch (Exception e) {
			randomNumber = upperBound;
		}

		return randomNumber;

	}

	/**
	 * The "PauseTestExecution" function is used to pause run time execution as
	 * per given time*
	 *
	 * @param sec
	 *            pause time based on second at time time
	 * @author Daffodil Software Private Limited
	 * @version 1.0
	 * @since 01.08.2021
	 */
	public static void PauseTestExecution(int sec) {

		if (sec > 5)// show a warnig message, if test execution is paused more
			// than 5 seconds from
			// any
			System.out.println("Test Execution paused for " + sec);

		try {
			Thread.sleep(1000 * sec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The "userLogin" function is used to login into the application
	 *
	 * @param test
	 *            Extent Test Instance
	 * @param driver
	 *            Selenium WebDriver Instance *
	 * @param email
	 *            Pass email id at run time as per need
	 * @param passord
	 *            Enter password at run time
	 * @author Daffodil Software Private Limited
	 * @version 1.0
	 * @since 01.08.2021
	 */
	public static void userLogin(ExtentTest test, WebDriver driver,
			String email, String Password) throws InterruptedException {

		// Click on email id field and input the email.
		IElementActions.clickAndInput_usingInterface(test, driver,
				XpathInterface.LoginForm.emailField, email, "EmailId");

		// Click on password field and input the password.
		IElementActions.clickAndInput_usingInterface(test, driver,
				XpathInterface.LoginForm.passwordField, Password, "Password");

		// Click on login button
		IElementActions.clickelement(node, driver,
				XpathInterface.LoginForm.loginbutton);
		node.log(Status.INFO, "FUNCTION-SUB STEP1 :Clicked on login button");
	}

}

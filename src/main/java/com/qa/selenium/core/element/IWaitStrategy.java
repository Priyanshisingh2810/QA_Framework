package com.qa.selenium.core.element;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.qa.baseclass.Baseclass;
import com.qa.utils.Utils;

public class IWaitStrategy extends Baseclass {

	/**
	 * The "waitForVisiblity" function is used to wait for visibility of any of
	 * the element present on webPage
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
	public static void waitForVisiblity(ExtentTest test, WebDriver driver,
			String Xpath) {

		WebDriverWait wait1 = new WebDriverWait(driver, 50);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath(Xpath)));
	}

	/**
	 * The "waitForInVisiblity" function is used to wait for invisibility of any
	 * of the element present on webPage
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
	public static void waitForInVisiblity(ExtentTest test, WebDriver driver,
			String Xpath) {

		WebDriverWait wait2 = new WebDriverWait(driver, 50);
		wait2.until(ExpectedConditions.invisibilityOfElementLocated(By
				.xpath(Xpath)));
	}

	/**
	 * The "WaitUntilPopupCloses" function is used to until pop up close*
	 *
	 * @param driver
	 *            Selenium WebDriver Instance *
	 * @param elem
	 *            element at run time
	 * @author Daffodil Software Private Limited
	 * @version 1.0
	 * @since 01.08.2021
	 */
	public static void WaitUntilPopupCloses(ExtentTest test, WebDriver driver,
			WebElement elem) {

		WebDriverWait wait = new WebDriverWait(driver, 5);

		final WebElement element = elem;
		wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				String enabled = element.getAttribute("style");
				if (enabled.contains("display: none"))
					return true;
				else
					return false;
			}
		});
	}

	/**
	 * The "WaitUntilElementClickable" function is used to wait for element at
	 * run time till element is clickable*
	 *
	 * @param driver
	 *            Selenium WebDriver Instance *
	 * @param e1
	 *            element at run time
	 * @author Daffodil Software Private Limited
	 * @version 1.0
	 * @since 01.08.2021
	 */
	public static boolean WaitUntilElementClickable(ExtentTest test,
			WebDriver driver, final WebElement el) {

		// set default wait timeout to zero.
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

		boolean elclickable = true;
		try {
			// Now set specific timeout.
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(el));
			wait.until(ExpectedConditions.elementToBeClickable(el));

		} catch (TimeoutException e) {
			elclickable = false;
			System.out.println("Finished waiting...");
		}

		// Re-set default wait timeout
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		return elclickable;
	}

	/**
	 * The "WaitUntilDropdownsLoaded" function is used to wait for all drop down
	 * element till loaded at run time
	 *
	 * @param driver
	 *            Selenium WebDriver Instance *
	 * @param e1
	 *            element at run time
	 * @author Daffodil Software Private Limited
	 * @version 1.0
	 * @since 01.08.2021
	 */
	@SuppressWarnings("deprecation")
	public static boolean WaitUntilDropdownsLoaded(ExtentTest test,
			WebDriver driver, final Select el) {

		// set default wait timeout
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

		boolean drpdowncanbeseleccted = true;
		try {
			new FluentWait<WebDriver>(driver).withTimeout(3, TimeUnit.SECONDS)
					.pollingEvery(300, TimeUnit.MICROSECONDS)
			.ignoring(NoSuchElementException.class)
					.until(new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver wd) {
					return (el.getOptions().size() > 1);
				}
			});
		} catch (TimeoutException e) {
			drpdowncanbeseleccted = false;
		}

		// Re-set default wait timeout
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		return drpdowncanbeseleccted;

	}

	/**
	 * The "WaitForPageLoadingToComplete" function is used to wait till page
	 * loading completes*
	 *
	 * @param driver
	 *            Selenium WebDriver Instance *
	 * @author Daffodil Software Private Limited
	 * @version 1.0
	 * @since 01.08.2021
	 */
	public static void WaitForPageLoadingToComplete(ExtentTest test,
			WebDriver driver) {

		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript(
						"return document.readyState").equals("complete");
			}
		};

		try {

			// Set page load timeout to minimum.
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

			Wait<WebDriver> wait = new WebDriverWait(driver, 10);
			wait.until(expectation);
		} catch (Exception e) {

			System.out
					.println("Page load doesnt complete in the timeout specified (30 Sec).Site slowness needs to be checked.");

		}

		Utils.PauseTestExecution(2);// Additional Wait - which is found useful
		// to handle specific cases.

		// Reset page load timeout.
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);

	}

	/**
	 * The "RegrehPage" function is used to refresh or reload any page*
	 *
	 * @param driver
	 *            Selenium WebDriver Instance *
	 * @author Daffodil Software Private Limited
	 * @version 1.0
	 * @since 01.08.2021
	 */

	public static void RefreshPage(ExtentTest test, WebDriver driver) {

		driver.navigate().refresh();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			WaitForPageLoadingToComplete(test, driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * The "WaitUntilElementLoadedWithSpecificTimeout" function is used to wait
	 * for element at run time till element is loaded* in a speciic given time
	 *
	 * @param driver
	 *            Selenium WebDriver Instance *
	 * @param e1
	 *            element at run time
	 * @param timeout
	 *            mention time
	 * @author Daffodil Software Private Limited
	 * @version 1.0
	 * @since 01.08.2021
	 */
	@SuppressWarnings("deprecation")
	public static boolean WaitUntilElementLoadedWithSpecificTimeout(
			ExtentTest test, WebDriver driver, final WebElement el, int timeout) {

		WaitForPageLoadingToComplete(test, driver);

		boolean elementfound = true;

		try {
			new FluentWait<WebDriver>(driver)
			.withTimeout(timeout, TimeUnit.SECONDS)
					.pollingEvery(500, TimeUnit.MILLISECONDS)
			.ignoring(NoSuchElementException.class)
					.until(new ExpectedCondition<Boolean>() {
						@Override
						public Boolean apply(WebDriver wd) {
							return (el.isDisplayed() && el.isEnabled());
						}
					});
		} catch (Exception e) {
			elementfound = false;
		}

		return elementfound;

	}

	/**
	 * The "WaitUntilElementLoaded" function is used to wait for element at run
	 * time*
	 *
	 * @param driver
	 *            Selenium WebDriver Instance *
	 * @param e1
	 *            element at run time
	 * @author Daffodil Software Private Limited
	 * @version 1.0
	 * @since 01.08.2021
	 */

	@SuppressWarnings("deprecation")
	public static boolean WaitUntilElementLoaded(ExtentTest test,
			WebDriver driver, final WebElement el) {

		// set default wait timeout
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		boolean elementfound = true;

		try {
			new FluentWait<WebDriver>(driver).withTimeout(10, TimeUnit.SECONDS)
			.pollingEvery(500, TimeUnit.MILLISECONDS)
					.ignoring(NoSuchElementException.class)
			.ignoring(StaleElementReferenceException.class)
					.until(new ExpectedCondition<Boolean>() {
						@Override
						public Boolean apply(WebDriver wd) {
							return (el.isDisplayed() && el.isEnabled());
						}
					});
		} catch (Exception e) {
			elementfound = false;
			System.out.println("Finished waiting..!.. ");
		}

		// Re-set default wait timeout
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		return elementfound;

	}

	/**
	 * The "WaitUntilSearchingDone" function is used to wait till element is
	 * searching at run time*
	 *
	 * @param driver
	 *            Selenium WebDriver Instance *
	 * @param e1
	 *            element at run time
	 * @author Daffodil Software Private Limited
	 * @version 1.0
	 * @since 01.08.2021
	 */
	@SuppressWarnings("deprecation")
	public static void WaitUntilSearchingDone(WebDriver driver,
			final WebElement el) {

		try {
			new FluentWait<WebDriver>(driver).withTimeout(60, TimeUnit.SECONDS)
			.pollingEvery(2, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class)
			.until(new ExpectedCondition<Boolean>() {
						@Override
						public Boolean apply(WebDriver wd) {
							return (!el.isDisplayed());
						}
					});
		} catch (Exception e) {
			System.out.println("Finished waiting..!.. ");
		}

	}

}

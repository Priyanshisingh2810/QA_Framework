package com.qa.selenium.core.driver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import com.aventstack.extentreports.ExtentTest;
import com.qa.baseclass.Baseclass;

public class IWebWindow extends Baseclass {

	public static void switchWindow(ExtentTest test) throws AWTException {
		// Use robot class for window switch
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_TAB);

		// Release key Ctrl+Shift+i
		robot.keyRelease(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_ALT);
	}

	public static void switchTab(ExtentTest test) throws AWTException {
		// Use robot class for tab switch
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_T);

		// Release key tab+control
		robot.keyRelease(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}
}

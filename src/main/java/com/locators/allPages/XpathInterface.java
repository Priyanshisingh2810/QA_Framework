package com.locators.allPages;

public interface XpathInterface {

	interface Site {
		String loginPageView = "(//span[contains(text(),'TestYou Login')])[2]";
	}

	interface LoginForm {
		String emailField = "(//input[contains(@id,'UserLogin')])[2]";
		String passwordField = "(//input[contains(@id,'Password')])[2]";
		String loginbutton = "//input[contains(@id,'btnLoginn')]";

	}

	interface DashBoard {
		String tabView = "//div[@class='main_wraper']";
	}
}

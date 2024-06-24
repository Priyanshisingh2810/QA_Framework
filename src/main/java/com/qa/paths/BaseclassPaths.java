package com.qa.paths;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.qa.baseclass.Baseclass;

public class BaseclassPaths extends Baseclass {

	public static String environment;
	public static String siteUrl;
	public static String baseUrl;

	public static void property() throws IOException {

		environment = configFile.getProperty("Environment");

		if (environment.equalsIgnoreCase("QA")) {

			siteUrl = "https://ui.cogmento.com/";
			baseUrl = "QA";

			// Object for QA Environment file that have all values/test data
			// used till now
			File UrlData = new File(System.getProperty("user.dir") + "/UrlFile/QA.properties");

			// Object for QA Environment file that have all Credentails
			File Credentails = new File(System.getProperty("user.dir") + "/Credentials/QA.properties");

			FileInputStream fileInput1 = null;
			FileInputStream fileInput2 = null;

			fileInput1 = new FileInputStream(UrlData);
			fileInput2 = new FileInputStream(Credentails);

			urlData.load(fileInput1);
			credentials.load(fileInput2);

		} else if (environment.equalsIgnoreCase("Staging"))

		{
			siteUrl = "https://ui.cogmento.com/";
			baseUrl = "staging";

			// Object for STAGING Environment file that have all values/test
			// data used till now
			File UrlData = new File(System.getProperty("user.dir") + "/UrlFile/Staging.properties");

			// Object for STAGING Environment file that have all Credentails
			File Credentails = new File(System.getProperty("user.dir") + "/Credentials/Staging.properties");

			FileInputStream fileInput1 = null;
			FileInputStream fileInput2 = null;

			fileInput1 = new FileInputStream(UrlData);
			fileInput2 = new FileInputStream(Credentails);

			urlData.load(fileInput1);
			credentials.load(fileInput2);

		}

		else if (environment.equalsIgnoreCase("Dev"))

		{
			siteUrl = "https://ui.cogmento.com/";
			baseUrl = "dev";

			// Object for DEV Environment file that have all values/test
			// data used till now
			File UrlData = new File(System.getProperty("user.dir") + "/UrlFile/Dev.properties");

			// Object for DEV Environment file that have all Credentails
			File Credentails = new File(System.getProperty("user.dir") + "/Credentials/Dev.properties");

			FileInputStream fileInput1 = null;
			FileInputStream fileInput2 = null;

			fileInput1 = new FileInputStream(UrlData);
			fileInput2 = new FileInputStream(Credentails);

			urlData.load(fileInput1);
			credentials.load(fileInput2);
		}

	}
}

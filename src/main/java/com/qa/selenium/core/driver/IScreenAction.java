package com.qa.selenium.core.driver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.qa.baseclass.Baseclass;

public class IScreenAction extends Baseclass {

	/**
	 * The "getFileExtenstion" function will convert date format and will return
	 * format in string*
	 *
	 * @author Daffodil Software Private Limited
	 * @version 1.0
	 * @since 01.08.2021
	 */
	public static String getFileExtension() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hhmmss");
		String fileExtn = f.format(new Date()) + " "
				+ (calendar.get(Calendar.AM_PM) == 1 ? "PM" : "AM");
		return fileExtn;
	}

	/**
	 * The "createDirectoryIfNotExit" function is used to create folder if not
	 * exit.
	 *
	 * @param folderName
	 *            User can give any folder name
	 * @author Daffodil Software Private Limited
	 * @version 1.0
	 * @since 01.08.2021
	 ***/
	public static File createDirectoryIfNotExist(String folderName)
			throws InterruptedException {
		String rootPath = System.getProperty("user.dir");
		File dirPath = new File(rootPath + "/Screenshot/" + folderName + ""
				+ getFileExtension() + "/");
		System.out.println(dirPath);
		if (!dirPath.exists()) {
			if (dirPath.mkdir()) {
				System.out.println("directory created successfully");
			} else {
				System.out.println("directory is not created");
			}
		}
		return dirPath;
	}

	public static File createDirectoryForReport() throws InterruptedException {
		String rootPath = System.getProperty("user.dir");
		File dirPath = new File(rootPath + "/QA-Report/");
		if (!dirPath.exists()) {
			if (dirPath.mkdir()) {
				System.out.println("Folder created for Report");
			} else {
				System.out.println("Folder not created");
			}
		}
		return dirPath;
	}

	public static void captureScreenShot_PassCase(String folderName,
			String filName, WebDriver driver) {
		try {

			File scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			File targetFile = new File(createDirectoryIfNotExist(folderName)
					+ "/" + filName + ".png");
			FileUtils.copyFile(scrFile, targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// return filName;
	}

	public static String captureScreenShot_FailedCase(WebDriver driver,
			String ScreenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyy-MM-dd hhmmss")
		.format(new Date());

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir")
				+ "/FailedTests_Screenshots/" + ScreenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;

	}

}

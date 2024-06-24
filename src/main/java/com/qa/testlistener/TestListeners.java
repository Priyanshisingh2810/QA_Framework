package com.qa.testlistener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.qa.baseclass.*;

public class TestListeners implements ITestListener {

	@Override
	public void onTestSkipped(ITestResult result) {

		Baseclass.extenttest = Baseclass.extent.createTest(result.getMethod().getDescription()).assignCategory("SkipedTest");
		Baseclass.node=Baseclass.extenttest.createNode("Report");
		Baseclass.node.log(Status.SKIP, result.getThrowable());
		Baseclass.node.log(Status.SKIP, result.getMethod().getDescription());
		Baseclass.node.log(Status.SKIP,	MarkupHelper.createLabel(result.getName(), ExtentColor.YELLOW));
		Baseclass.node.log(Status.INFO, "Test with name : " + result.getName()	+ " is Skipped as its depending test failed");

		Baseclass.extent.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}
}

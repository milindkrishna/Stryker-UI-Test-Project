package Stryker.com.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {

//	ExtentTest test;
//	ExtentReports extent = ExtentReporterNG.getReportObject();

	private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();
	private static ExtentReports extent = ExtentReporterNG.getReportObject();

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ExtentTest t = extent.createTest(result.getMethod().getMethodName());
		testThread.set(t);

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ExtentTest t = testThread.get();
		if (t != null) {
			t.log(Status.PASS, "Test Passed");
		}
		testThread.remove();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		// screenshot
		ExtentTest t = testThread.get();
		if (t == null) {
			// fallback if onTestStart didn't run or thread data lost
			t = extent.createTest(result.getMethod().getMethodName());
			testThread.set(t);
		}

		// log throwable if available
		if (result.getThrowable() != null) {
			t.fail(result.getThrowable());
		} else {
			t.fail("Test failed without throwable");
		}

		// obtain driver instance from test class to take screenshot
		WebDriver localDriver = null;
		try {
			localDriver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}

		// attach screenshot if possible
		if (localDriver != null) {
			try {
				String filepath = getScreenshot(result.getMethod().getMethodName(), localDriver);
				t.addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		testThread.remove();

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ExtentTest t = testThread.get();
        if (t != null) {
            t.log(Status.SKIP, "Test Skipped");
        }
        testThread.remove();

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

}

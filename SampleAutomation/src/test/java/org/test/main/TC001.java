package org.test.main;

import java.io.IOException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.test.base.Base;
import org.test.pom.FleetStudioWork;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TC001 extends Base{	
	FleetStudioWork page;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	@BeforeTest
	public void reportInitialization(){
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/target/Report/myReport.html");
		htmlReporter.config().setDocumentTitle("Fleetstudio Work Page Automation");
		htmlReporter.config().setReportName("Functional Report");
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "Local Host");
		extent.setSystemInfo("Browser", "Chrome Browser");
		extent.setSystemInfo("Tester", "Shiyam Sundar");
	}
	
	@BeforeClass
	public void browserLaunch() {
		launchUrl();
		pageTitle();
		pageUrl();
	}
	
	@BeforeMethod
	public void pageLaunch() {
		page = new FleetStudioWork();
		page.getBtnWork().click();
	}
	
	@Test
	public void tc001_1() throws IOException {
		test = extent.createTest("Testing QA Tag Page");
		page = new FleetStudioWork();
		WebElement element1 = page.getTag();
		selectOne(element1, "QA");
		Assert.assertTrue(driver.getCurrentUrl().contains("qa"));
		
		WebElement element2 = page.getQaProj();
		element2.click();
		Assert.assertTrue(driver.getCurrentUrl().contains("bmw"));
	}
	

	@Test
	public void tc001_2() throws IOException {
		test = extent.createTest("Testing PRODUCT DEV Tag Page");
		page = new FleetStudioWork();
		WebElement element1 = page.getTag();
		selectOne(element1, "Product Dev");
		Assert.assertTrue(driver.getCurrentUrl().contains("dev"));
		
		WebElement element2 = page.getDevProj();
		element2.click();
		Assert.assertTrue(driver.getCurrentUrl().contains("gallo"));
	}
	
	@Test
	public void tc001_3() throws IOException {
		test = extent.createTest("Testing ALL Tag Page");
		page = new FleetStudioWork();
		WebElement tag = page.getTag();
		Select s = new Select(tag);
		WebElement option = s.getFirstSelectedOption();
		if (option.getText().contains("ALL")) {
			
			WebElement element1 = page.getDevProj();
			element1.click();
			Assert.assertTrue(driver.getCurrentUrl().contains("gallo"));
			
			driver.navigate().back();
			WebElement element2 = page.getQaProj();
			element2.click();
			Assert.assertTrue(driver.getCurrentUrl().contains("bmw"));
		}
	}
	
	@Test
	public void tc001_4() {
		test = extent.createTest("Testing Next button is clickable or not ");
		page = new FleetStudioWork();
		WebElement elementNext = page.getElementNext();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", elementNext);
		Assert.assertFalse(enabled(elementNext));		
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "Test Case Failed is: "+ result.getName());
			test.log(Status.FAIL, "Test Case Failed is: "+ result.getThrowable());
			takePicture(result.getName());
		} else if(result.getStatus() == ITestResult.SUCCESS){
			test.log(Status.PASS, "Test Case Passed are: "+ result.getName());
		}
	}

	@AfterSuite
	public void aftSuite() {
		extent.flush();
	}
}

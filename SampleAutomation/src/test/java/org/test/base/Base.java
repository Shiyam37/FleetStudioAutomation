package org.test.base;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	public static WebDriver driver;
	public static Select s;
	
	public void launchUrl() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://fleetstudio.com/work");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void pageUrl() {
		System.out.println(driver.getCurrentUrl());
	}
	
	public void pageTitle() {
		System.out.println(driver.getTitle());
	}
	
	public void quitBrowser() {
		driver.quit();
	}

	//Visibility of WebElement
	public boolean enabled(WebElement e) {
		if (e.isEnabled()) {
			return true;
		}else {
			return false;
		}
	}
	
	//Take ScreenShot
	public void takePicture(String testMethodName) {
		TakesScreenshot t = (TakesScreenshot) driver;
		File src = t.getScreenshotAs(OutputType.FILE);
		File des = new File("C:\\Users\\hp\\eclipse-workspace\\SampleAutomation\\ScreenShot\\"
				+testMethodName+"_"+".jpg");
		try {
			FileUtils.copyFile(src, des);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Drop Down
	public void selectOne(WebElement e, String txt) {
		s = new Select(e);
		s.selectByVisibleText(txt);
	}

}

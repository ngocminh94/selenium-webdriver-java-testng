package webdriver;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_VIII_Page_Loading {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_() {
		driver.get("https://api.orangehrm.com/");
		
		System.out.println("Start time = " + new Date().toString());
		// Wait cho page
		Assert.assertTrue(isJQueryAndPageLoadedSuccess(driver));
		System.out.println("End time = " + new Date().toString());
		
		System.out.println("Start time = " + new Date().toString());
		// Wait cho element status
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='OrangeHRM REST API Documentation']")));
		System.out.println("End time = " + new Date().toString());
		
		System.out.println("Start time = " + new Date().toString());
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='OrangeHRM REST API Documentation']")).isDisplayed());
		System.out.println("End time = " + new Date().toString());

	}

	public boolean isJQueryLoadedSuccess(WebDriver driver) {
		ExpectedCondition<Boolean> jQueryLoaded = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.
						executeScript("return (window.jQuery !=null) && (jQuery.active === 0);");
			}
		};
		return explicitWait.until(jQueryLoaded);
	}
	
	public boolean isJQueryAndPageLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
		
		ExpectedCondition<Boolean> jQueryLoaded = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};
		
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		
		return explicitWait.until(jQueryLoaded) && explicitWait.until(jsLoad);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
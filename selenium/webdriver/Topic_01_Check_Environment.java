package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_01_Check_Environment {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://google.com");
	}

	@Test
	public void TC_01_ValidateCurrentUrl() {

	}

	@Test
	public void TC_02_ValidatePageTitle() {

	}

	@Test
	public void TC_03_LoginFormDisplayed() {

	}
	@Test
	public void TC_04_Test_Push_Code() {

	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
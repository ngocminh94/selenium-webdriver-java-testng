package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Default_Dropdown_List {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
	}
	
	@Test
	public void TC_01_NopCommerce() {
		driver.get("https://demo.nopcommerce.com/");
		
		String firstName = "";
		String lastName = "";
		String emailAddress = "";
		String day = "";
		String month = "";
		String year = "";
		String company = "";
		String password = "";
		
		driver.findElement(By.cssSelector("a.ico-register")).click();		
		driver.findElement(By.id("gender-male")).click();
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Company")).sendKeys(company);
		driver.findElement(By.id("Password")).sendKeys(null);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(null);
		driver.findElement(By.id("register-button")).sendKeys(null);
		
		
		
	}

	@Test
	public void TC_02_Rode() {
	
	}	
	
	@Test
	public void TC_03_() {
			
	}	
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
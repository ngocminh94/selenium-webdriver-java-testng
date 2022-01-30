package testng;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic_06_Loop {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Select select;
	JavascriptExecutor jsExecutor;

	String firstName, lastName, emailAddress, companyName, day, month, year;

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.get("https://demo.nopcommerce.com/");

		firstName = "Min";
		lastName = "Trinh";
		companyName = "Nexus";
		day = "15";
		month = "December";
		year = "2021";
	}

	@Test(invocationCount = 5)
	public void TC_01_Register_NopCommerce() {

		driver.findElement(By.cssSelector("a.ico-register")).click();

		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);

		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		select.selectByVisibleText(day);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);

		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText(month);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);

		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText(year);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);

		driver.findElement(By.id("Email")).sendKeys("min" + generateEmail());
		driver.findElement(By.id("Company")).sendKeys(companyName);

		driver.findElement(By.id("Password")).sendKeys("123456");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("123456");

		// driver.findElement(By.id("register-button")).click();
		clickToElementByJS("//button[@id='register-button']");

		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");

		driver.findElement(By.className("ico-logout")).click();
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public String generateEmail() {
		Random rand = new Random();
		return rand.nextInt(9999) + "@gmail.net";
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}

}

package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Xpath_Css_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); // mục đích là để chờ cho element đc render ra trước khi thao tác
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}

	@Test
	public void TC_01_Login_Empty_Email_And_Password() {
		// nhap du lieu vao textbox:
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.name("login[password]")).sendKeys("");

		// click vao button Login
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		// Get error messageg text of Email and Password
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),
				"This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),
				"This is a required field.");

	}

	@Test
	public void TC_02_Login_Invalid_Email() {
		// refresh current page
		driver.navigate().refresh();

		// nhap du lieu vao textbox
		driver.findElement(By.id("email")).sendKeys("12345@123.123");
		driver.findElement(By.name("login[password]")).sendKeys("123456");

		// click vao button Login
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		// Get error messageg text of Email
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(),
				"Please enter a valid email address. For example johndoe@domain.com.");

	}

	@Test
	public void TC_03_Login_Invalid_Password() {
		// refresh current page
		driver.navigate().refresh();

		// nhap du lieu vao textbox
		driver.findElement(By.id("email")).sendKeys("min@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123");

		// click vao button Login
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		// Get error messageg text of Password
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(),
				"Please enter 6 or more characters without leading or trailing spaces.");

	}

	@Test
	public void TC_04_Login_Incorrect_Email() {
		// refresh current page
		driver.navigate().refresh();

		// nhap du lieu vao textbox
		driver.findElement(By.id("email")).sendKeys("min@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123456");

		// click vao button Login
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		// Get error messageg text of Email
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

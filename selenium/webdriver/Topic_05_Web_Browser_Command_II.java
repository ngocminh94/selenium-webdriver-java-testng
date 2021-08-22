package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_Command_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Url() {
		driver.get("https://www.facebook.com/");

		driver.findElement(By.xpath("//a[text()='Đăng ký']")).click();

		String registerPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(registerPageUrl, "https://www.facebook.com/r.php");

		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();

		String loginPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl, "https://www.facebook.com/login/");

	}

	@Test
	public void TC_02_Title() {
		driver.get("https://www.facebook.com/");

		driver.findElement(By.xpath("//a[text()='Đăng ký']")).click();

		String registerPageTitle = driver.getTitle();
		Assert.assertEquals(registerPageTitle, "Đăng ký Facebook | Facebook");

		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();

		String loginPageTitle = driver.getTitle();
		Assert.assertEquals(loginPageTitle, "Đăng nhập Facebook");

	}

	@Test
	public void TC_03_Navigation() {
		driver.get("https://www.facebook.com/");

		driver.findElement(By.xpath("//a[text()='Đăng ký']")).click();

		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();

		driver.navigate().back();

		String registerPageTitle = driver.getTitle();
		Assert.assertEquals(registerPageTitle, "Đăng ký Facebook | Facebook");

		String registerPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(registerPageUrl, "https://www.facebook.com/r.php");

		driver.navigate().forward();

		String loginPageTitle = driver.getTitle();
		Assert.assertEquals(loginPageTitle, "Đăng nhập Facebook");

		String loginPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl, "https://www.facebook.com/login/");

	}

	@Test
	public void TC_04_Page_Source() {
		driver.get("https://www.facebook.com/");

		driver.findElement(By.xpath("//a[text()='Đăng ký']")).click();
		
		String registerPageSource = driver.getPageSource();
		Assert.assertTrue(registerPageSource.contains("Tạo tài khoản mới"));
		
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		
		String loginPageSourse = driver.getPageSource();
		Assert.assertTrue(loginPageSourse.contains("Đăng nhập Facebook"));

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
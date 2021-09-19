package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_IV_Static {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	By startButtonBy = By.cssSelector("div[id='start'] button");
	By loadingIconBy = By.cssSelector("#loading");
	By helloWorldTextBy = By.cssSelector("div[id='finish'] h4");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Less_Than() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(startButtonBy).click();
		
		// Time bị thiếu
		sleepInSecond(2);
		
		// Thiếu 2s

		Assert.assertEquals(driver.findElement(helloWorldTextBy).getText(), "Hello World!");

	}

	@Test
	public void TC_02_Enough() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(startButtonBy).click();
		
		// Time vừa đủ
		// khó biết bao nhiêu s là đủ vì mỗi lúc một time khác nhau
		sleepInSecond(5);

		Assert.assertEquals(driver.findElement(helloWorldTextBy).getText(), "Hello World!");

	}

	@Test
	public void TC_03_More_Than() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(startButtonBy).click();
		
		// Thừa time
		// vẫn pass tcs - chạy được, chạy đúng nhưng chưa tối ưu
		// mỗi tcs dư vài giây x tổng số tcs...
		sleepInSecond(10);

		Assert.assertEquals(driver.findElement(helloWorldTextBy).getText(), "Hello World!");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_III_Implicit {
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
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(startButtonBy).click();

		// Actual: sau khi click mất 5s thì helloWorldTextBy mới xuất hiện

		// Tìm element helloWorldTextBy
		// Cứ 0.5s tìm 1 lần -> Sau 2s -> ko thấy -> hết timeout

		Assert.assertEquals(driver.findElement(helloWorldTextBy).getText(), "Hello World!");

	}

	@Test
	public void TC_02_Enough() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(startButtonBy).click();

		// Actual: sau khi click mất 5s thì helloWorldTextBy mới xuất hiện

		// Tìm element helloWorldTextBy
		// Cứ 0.5s tìm 1 lần -> Sau 5s -> thấy

		Assert.assertEquals(driver.findElement(helloWorldTextBy).getText(), "Hello World!");

	}

	@Test
	public void TC_03_More_Than() {
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(startButtonBy).click();

		// Actual: sau khi click mất 5s thì helloWorldTextBy mới xuất hiện

		// Tìm element helloWorldTextBy
		// Cứ 0.5s tìm 1 lần -> Sau 5s -> thấy
		// Giây 6,7,8 ko tìm nữa, thực hiện step tiếp theo nếu có

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
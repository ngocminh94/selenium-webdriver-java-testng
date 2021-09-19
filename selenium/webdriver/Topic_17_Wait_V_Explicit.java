package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_V_Explicit {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	By startButtonBy = By.cssSelector("div[id='start'] button");
	By loadingIconBy = By.cssSelector("#loading");
	By helloWorldTextBy = By.cssSelector("div[id='finish'] h4");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		explicitWait = new WebDriverWait(driver, 15);

		driver.manage().window().maximize();
	}

	public void TC_01_() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		explicitWait = new WebDriverWait(driver, 15);

		// Trước khi click thì wait cho Start Button có thể click đc
		explicitWait.until(ExpectedConditions.elementToBeClickable(startButtonBy));
		driver.findElement(startButtonBy).click();

		explicitWait = new WebDriverWait(driver, 3);

		// Business: Nếu loading icon biến mất = Hello world hiển thị

		// Wait cho loading icon biến mất
		// explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIconBy));

		// Wait cho helloWorldTextBy hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloWorldTextBy));

		Assert.assertEquals(driver.findElement(helloWorldTextBy).getText(), "Hello World!");

	}

	public void TC_02_Enough() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		explicitWait = new WebDriverWait(driver, 15);

		// Trước khi click thì wait cho Start Button có thể click đc
		explicitWait.until(ExpectedConditions.elementToBeClickable(startButtonBy));
		driver.findElement(startButtonBy).click();

		explicitWait = new WebDriverWait(driver, 5);

		// Business: Nếu loading icon biến mất = Hello world hiển thị

		// Wait cho loading icon biến mất
		// explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIconBy));

		// Wait cho helloWorldTextBy hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloWorldTextBy));

		Assert.assertEquals(driver.findElement(helloWorldTextBy).getText(), "Hello World!");

	}

	public void TC_03_() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		explicitWait = new WebDriverWait(driver, 15);

		// Trước khi click thì wait cho Start Button có thể click đc
		explicitWait.until(ExpectedConditions.elementToBeClickable(startButtonBy));
		driver.findElement(startButtonBy).click();

		explicitWait = new WebDriverWait(driver, 15);

		// Business: Nếu loading icon biến mất = Hello world hiển thị

		// Wait cho loading icon biến mất
		// explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIconBy));

		// Wait cho helloWorldTextBy hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloWorldTextBy));

		Assert.assertEquals(driver.findElement(helloWorldTextBy).getText(), "Hello World!");

	}

	@Test
	public void TC_04_Ajax_Loading() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		WebElement textToday = driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1"));

		Assert.assertEquals(textToday.getText(), "No Selected Dates to display.");

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='1']")));

		driver.findElement(By.xpath("//a[text()='1']")).click();

		explicitWait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']")));

		Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(),
				"Wednesday, September 1, 2021");
		
		explicitWait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']/a[text()='1']")));

		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']/a[text()='1']")).isDisplayed());

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
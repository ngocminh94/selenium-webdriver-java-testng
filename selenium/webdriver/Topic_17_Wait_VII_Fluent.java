package webdriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_17_Wait_VII_Fluent {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void TC_01_() {

	}
	
	public void TC_02_Implicit() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);

		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='javascript_countdown_time' and text()='01:01:00']")).isDisplayed());

	}

	public void TC_03_Explicit() {
		driver.get("https://automationfc.github.io/fluent-wait/");

		explicitWait = new WebDriverWait(driver, 13);

		explicitWait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@id='javascript_countdown_time' and text()='01:01:00']")));

	}
	
	@Test
	public void TC_04_Fluent() {
		driver.get("https://automationfc.github.io/fluent-wait/");

		WebElement countdownTime = driver.findElement(By.id("javascript_countdown_time"));
		
		FluentWait<WebElement> fluentWait = new FluentWait<WebElement>(countdownTime);
		
		fluentWait.withTimeout(Duration.ofSeconds(13))
		.pollingEvery(Duration.ofMillis(100))
		.ignoring(NoSuchElementException.class)
		.until(new Function<WebElement, Boolean>(){
			public Boolean apply(WebElement countdown) {
				return countdown.getText().endsWith("00");
			}
		});

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
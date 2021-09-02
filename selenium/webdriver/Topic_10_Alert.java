package webdriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Alert {
	WebDriver driver;
	Alert alert;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String authenChromeAutoIT = projectPath + "\\autoIT\\authen_chrome.exe";
	String autheFirefoxAutoIT = projectPath + "\\autoIT\\authen_firefox.exe";

	@BeforeClass
	public void beforeClass() {
		// System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		// driver = new ChromeDriver();

		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		explicitWait = new WebDriverWait(driver, 15);

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	
	public void TC_01_Accept_Alert() {
		driver.get("http://demo.guru99.com/v4/index.php");

		driver.findElement(By.name("btnLogin")).click();
		sleepInSecond(2);

		// Wait cho Alert xuat hien trong vong xx giay (15)
		// Wait + switch to alert
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		// Get text cua alert: alert chua bien mat
		Assert.assertEquals(alert.getText(), "User or Password is not valid");

		// Accept 1 alert -> alert se bien mat ( = click OK)
		alert.accept();

	}

	
	public void TC_02_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(2);

		alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals(alert.getText(), "I am a JS Alert");

		alert.accept();

		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(), "You clicked an alert successfully");

	}

	
	public void TC_03_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// 1 - Accept
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		sleepInSecond(2);
		
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(), "You clicked: Ok");
		
		// 2 - Cancel
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		sleepInSecond(2);

		alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals(alert.getText(), "I am a JS Confirm");

		alert.dismiss();

		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(), "You clicked: Cancel");

	}

	
	public void TC_04_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// 1 - Accept
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		sleepInSecond(2);
		
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		String addressName = "Ho Chi Minh";
		alert.sendKeys(addressName);
		sleepInSecond(2);
		
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(), "You entered: " + addressName);
		
		// 2 - Cancel
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		sleepInSecond(3);
		
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertEquals(alert.getText(), "I am a JS prompt");

		alert.sendKeys(addressName);
		sleepInSecond(3);
		
		alert.dismiss();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(), "You entered: null");

	}

	
	public void TC_05_Authentication_Alert() {
		String username = "admin";
		String password = "admin";
		String url = "http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth";
		driver.get(url);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		
	}
	
	
	@Test
	public void TC_06_Authentication_Alert() {
		String username = "admin";
		String password = "admin";
		
		driver.get("http://the-internet.herokuapp.com");
		
		String basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		
		driver.get(getLinkByUserPass(basicAuthenLink, username, password));
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		
	}
	
	public String getLinkByUserPass(String link, String username, String password) {
		// http://the-internet.herokuapp.com/basic_auth
		String[] links = link.split("//");
		// links[0] = http:
		// links[1] = the-internet.herokuapp.com/basic_auth
		
		return links[0] + "//" + username + ":" + password + "@" + links[1];		
	}
	
	public void TC_07_Authentication_Alert_AutoIT() throws IOException {
		String username = "admin";
		String password = "admin";
		String url = "http://the-internet.herokuapp.com/basic_auth";
		
		if(driver.toString().contains("chrome")) {
			Runtime.getRuntime().exec(new String[] { authenChromeAutoIT, username, password});
		} else {
			Runtime.getRuntime().exec(new String[] { autheFirefoxAutoIT, username, password});
		}
		
		driver.get(url);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		
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
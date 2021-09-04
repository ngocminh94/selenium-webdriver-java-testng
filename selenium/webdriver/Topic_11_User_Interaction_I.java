package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_User_Interaction_I {
	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		action = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	
	public void TC_01_Hover_Mouse_I() {
		driver.get("https://tiki.vn/");
		
		// Hover chuột vào icon
		action.moveToElement(driver.findElement(By.cssSelector(".profile-icon"))).perform();
		sleepInSecond(1);
		
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='tel']")).isDisplayed());						
		
	}

	
	public void TC_01_Hover_Mouse_II() {
		driver.get("https://www.myntra.com/");
		
		action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Kids']"))).perform();
		sleepInSecond(1);
		
		driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Home & Bath']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text()='Kids Home Bath']")).isDisplayed());
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.myntra.com/kids-home-bath");
			
	}
	
	
	public void TC_02_Click_And_Hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List<WebElement> rectangleNumber = driver.findElements(By.cssSelector("#selectable>li"));
		System.out.println("Number of rectangle = " + rectangleNumber.size());
		
		// Click and hold vào element đầu tiên -> hover chuột đến element đích -> nhả chuột ra
		action.clickAndHold(rectangleNumber.get(1)).moveToElement(rectangleNumber.get(10)).release().perform();
		sleepInSecond(2);
	
		Assert.assertEquals(driver.findElements(By.cssSelector("#selectable>li.ui-selected")).size(), 6);

	}
	
	@Test
	public void TC_02_Click_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List<WebElement> rectangleNumber = driver.findElements(By.cssSelector("#selectable>li"));

		// Nhấn Ctrl xuống
		action.keyDown(Keys.CONTROL).perform();
		
		// Chọn các element đích: 1 3 6 11
		action.click(rectangleNumber.get(0))
		.click(rectangleNumber.get(2))
		.click(rectangleNumber.get(5))
		.click(rectangleNumber.get(10)).perform();
		
		// Nhả Ctrl ra
		action.keyUp(Keys.CONTROL).perform();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElements(By.cssSelector("#selectable>li.ui-selected")).size(), 4);

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